# GymPa - Architecture & Development Guide

## 1. Architecture Overview
We are using **MVVM (Model-View-ViewModel)** with **Clean Architecture** principles. This ensures separation of concerns, making the app testable and scalable.

### Layers
1.  **Data Layer (The "Model")**:
    *   **Source of Truth**: Room Database (Local storage).
    *   **Entities**: Data classes representing database tables (`WorkoutSession`, `Exercise`, `ExerciseSet`).
    *   **DAO (Data Access Object)**: Interface to access the database.
    *   **Repository**: A clean API for the UI to access data. It hides the database complexity.

2.  **DI Layer (Dependency Injection)**:
    *   **Hilt**: Manages object creation. We don't create `Database` or `Repository` manually; Hilt gives them to us.

3.  **UI Layer (The "View" & "ViewModel")**:
    *   **Jetpack Compose**: For building the UI.
    *   **ViewModels**: Holds the state for the UI (e.g., "Is the list loading?", "What are the exercises today?"). It talks to the Repository.
    *   **Navigation**: Handles moving between screens (e.g., Home -> Session Details).

---

## 2. Proposed File Structure
This is how we will organize the code in `app/src/main/java/com/example/gympa/`:

```text
com.example.gympa
├── GymPaApplication.kt          <-- Hilt Application entry point
├── MainActivity.kt              <-- Single Activity
│
├── data
│   ├── local                    <-- Database related code
│   │   ├── AppDatabase.kt
│   │   ├── GymDao.kt
│   │   └── GymEntities.kt       <-- Tables (Session, Exercise, Set)
│   │
│   └── repository               <-- Repository Pattern
│       └── GymRepository.kt
│
├── di                           <-- Hilt Modules
│   └── AppModule.kt
│
├── ui
│   ├── theme                    <-- Colors, Typography (Auto-generated)
│   │
│   ├── navigation               <-- Navigation Logic
│   │   └── AppNavigation.kt     <-- NavHost definition
│   │
│   ├── home                     <-- Screen: List of Workout Days
│   │   ├── HomeViewModel.kt
│   │   └── HomeScreen.kt
│   │
│   └── session                  <-- Screen: Exercise Tracker for a specific day
│       ├── SessionViewModel.kt
│       └── SessionScreen.kt
│
└── util                         <-- Helpers (Date formatters, etc.)
```

---

## 3. Implementation Steps

### Phase 1: Foundation (Data & DI)
1.  **Setup Dependencies**: Add Room, Hilt, Navigation to `build.gradle.kts`.
2.  **Define Data Models**: Create `GymEntities.kt` with Room annotations.
3.  **Create DAO**: Define `GymDao.kt` for SQL queries.
4.  **Database Setup**: Create `AppDatabase.kt`.
5.  **Repository**: Create `GymRepository.kt` to expose data to ViewModels.
6.  **Dependency Injection**: Configure `AppModule.kt` to provide Database and Repository instances.

### Phase 2: UI - Home Screen (Activity Tracker)
7.  **Home ViewModel**: Create `HomeViewModel.kt` to fetch the list of workout sessions.
8.  **Home Screen**: Create `HomeScreen.kt` to display a list of dates (sessions).
9.  **Navigation Setup**: Create `AppNavigation.kt` and set up the start destination.

### Phase 3: UI - Session Detail (Exercise Tracker)
10. **Session ViewModel**: Create `SessionViewModel.kt` to manage exercises for a specific session ID.
11. **Session Screen**: Create `SessionScreen.kt` to:
    *   Show list of exercises.
    *   Add new exercises.
    *   Add sets (Weight/Reps) to exercises.
12. **Connect Navigation**: Allow clicking a session in Home to open Session Detail.

---

## 4. Key Concepts to Learn
*   **State Hoisting**: Moving state up to the ViewModel so the UI is stateless.
*   **Flow**: Used to observe database changes in real-time.
*   **Coroutines**: For performing database operations off the main thread.
*   **Scaffold**: Basic layout structure (TopBar, FAB, Content).