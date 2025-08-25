package com.example.todo.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo._updateScreen
import com.example.todo.todoViewModel


@Composable
fun DetailScreen(navController: NavHostController,viewModel: todoViewModel, id:Int) {

    val todoLiveData = viewModel.getTodoById(id)
    val todo by todoLiveData.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(25.dp),

    ) {
        Spacer(Modifier.height(50.dp))
        Row (
            modifier = Modifier.fillMaxWidth().padding(10.dp).height(30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "View Note", fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,fontFamily = FontFamily.Monospace
            )
            IconButton(onClick = {
                navController.navigate(_updateScreen +"/$id")
            }) {
                Icon(Icons.Default.Edit, contentDescription = "edit")
            }
            IconButton(onClick = {
                viewModel.deleteTodo(id)
                navController.popBackStack()
            }) {
                Icon(Icons.Default.Delete, contentDescription = "delete")
            }
        }
        Spacer(Modifier.height(25.dp))
        Text(text = todo?.text ?: "", fontSize = 20.sp, fontFamily = FontFamily.Monospace)
        Spacer(Modifier.height(25.dp))


    }
}