package com.example.gympa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gympa.components.BottomNavigationBar
import com.example.gympa.navigation.BottomNavItem
import com.example.gympa.screen.DashboardScreen
import com.example.gympa.screen.HistoryScreen
import com.example.gympa.screen.SettingsScreen
import com.example.gympa.screen.WorkoutScreen
import com.example.gympa.ui.theme.GymPaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GymPaTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val showBottomBar = currentRoute in listOf("home", "session", "history", "settings")
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if(showBottomBar){
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = BottomNavItem.Home.route,
                        modifier = Modifier.padding(innerPadding)) {
                        composable(BottomNavItem.Home.route) {
                            DashboardScreen()
                        }
                        composable(BottomNavItem.Session.route) {
                            WorkoutScreen()
                        }
                        composable(BottomNavItem.History.route) {
                            HistoryScreen()
                        }
                        composable(BottomNavItem.Settings.route) {
                            SettingsScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GymPaTheme {
        Greeting("Android")
    }
}