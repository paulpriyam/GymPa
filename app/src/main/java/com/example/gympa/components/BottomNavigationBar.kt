package com.example.gympa.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.gympa.R
import com.example.gympa.navigation.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
val screens = listOf(
    BottomNavItem.Home,
    BottomNavItem.Session,
    BottomNavItem.History,
    BottomNavItem.Settings
)
    val currentRoute = navController.currentDestination?.route
    NavigationBar() {
        screens.forEach { screen ->
            NavigationBarItem(
selected = screen.route == currentRoute,
                onClick = {

                    navController.navigate(screen.route){
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = {
                    Text(text = screen.label)
                },
                icon = {
                    Icon(imageVector = screen.icon, contentDescription = screen.label)
                }
            )
        }
    }
}

