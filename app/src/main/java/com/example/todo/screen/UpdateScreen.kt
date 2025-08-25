package com.example.todo.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.todo.todoViewModel


@Composable
fun UpdateScreen(navController: NavHostController, viewModel: todoViewModel, id: Int) {

    val todoLiveData = viewModel.getTodoById(id)
    val todo by todoLiveData.observeAsState()


    var text by remember { mutableStateOf("") }

    LaunchedEffect(todo) {
        todo?.let {
            text = it.text
        }
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(25.dp),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Spacer(Modifier.height(30.dp))
        Text(text = "Update Note ", fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,fontFamily = FontFamily.Monospace
        )

        Spacer(Modifier.height(30.dp))

        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Write Something") },
            modifier = Modifier.fillMaxWidth().height(150.dp),
            shape = RoundedCornerShape(10.dp),
            maxLines = 5,



        )

        Spacer(Modifier.height(30.dp))

        Button(onClick ={
            viewModel.updateTodo(text, id)
            navController.popBackStack()
        },
            modifier = Modifier.fillMaxWidth().height(40.dp),
            shape = RoundedCornerShape(10.dp)
        )
        {
            Text(text = "SAVE", fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,fontFamily = FontFamily.Monospace
            )
        }
    }

}