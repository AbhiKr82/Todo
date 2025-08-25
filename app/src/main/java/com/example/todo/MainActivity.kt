package com.example.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todo.screen.AddScreen
import com.example.todo.screen.DetailScreen
import com.example.todo.screen.HomeScreen
import com.example.todo.screen.UpdateScreen
import com.example.todo.ui.theme.TODOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Navigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Navigation(modifier: Modifier = Modifier) {

    val navController = rememberNavController()
    val viewModel: todoViewModel = viewModel()

    NavHost(navController = navController, startDestination = _homeScreen) {
        composable(_homeScreen) {
            HomeScreen(navController,viewModel)
        }
        composable(_addScreen) {
            AddScreen(navController,viewModel)
        }
        composable(_detailScreen+"/{id}") {
            val id = it.arguments?.getString("id") ?: "-1"
            DetailScreen(navController, viewModel, id.toInt())
        }
        composable(_updateScreen+"/{id}") {
            val id = it.arguments?.getString("id") ?: "-1"
            UpdateScreen(navController, viewModel, id.toInt())
        }
    }

}

