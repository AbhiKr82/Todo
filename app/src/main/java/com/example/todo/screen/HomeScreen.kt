package com.example.todo.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo._addScreen
import com.example.todo._detailScreen
import com.example.todo.model.Todo
import com.example.todo.todoViewModel


@Composable
fun HomeScreen(navController: NavHostController, viewModel: todoViewModel) {

    val todoList by viewModel.todoList.observeAsState(initial = listOf())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(_addScreen) },

            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(Modifier.height(10.dp))
            Text(text = "All Note", fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,fontFamily = FontFamily.Monospace
            )

            Spacer(Modifier.height(10.dp))
            LazyColumn {
                items(todoList.size) { index ->

                    TODOItem(todoList[index],viewModel,navController)
                }

            }

        }
    }
}

@Composable
fun TODOItem(todo: Todo, viewModel: todoViewModel,navController: NavHostController) {
    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(100.dp)
            .clickable(
                onClick = { navController.navigate(_detailScreen+"/${todo.id}") }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = todo.text,
                modifier = Modifier.weight(1f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                 fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,fontFamily = FontFamily.Monospace

            )
            IconButton(
                onClick = { viewModel.deleteTodo(todo.id) }
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }
    }
}
