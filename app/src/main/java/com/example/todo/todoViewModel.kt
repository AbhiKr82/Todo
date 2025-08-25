package com.example.todo


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo.db.MyApp
import com.example.todo.model.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class todoViewModel: ViewModel() {

    val todoDao = MyApp.database.todoDao()

    val todoList : LiveData<List<Todo>> =todoDao.getAllData()

    fun addTodo(text: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var todo = Todo(text = text, createdAt = Date.from(Instant.now()))
            todoDao.insertData(todo)
        }

    }

    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteData(id)
        }

    }

    fun getTodoById(id: Int): LiveData<Todo> {
        return todoDao.getTodoById(id)

    }
    fun updateTodo(text: String, id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.updateData(text, id)
        }

    }
}

