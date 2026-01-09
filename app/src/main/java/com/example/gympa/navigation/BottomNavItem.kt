package com.example.gympa.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
){
    object Home: BottomNavItem("home", "Home", Icons.Default.Home)
    object Session: BottomNavItem("session", "Session", Icons.Default.FitnessCenter)
    object History: BottomNavItem("history", "History", Icons.Default.History)
    object Settings: BottomNavItem("settings", "Settings", Icons.Default.Settings)
}