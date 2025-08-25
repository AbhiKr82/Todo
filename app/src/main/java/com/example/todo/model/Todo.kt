package com.example.todo.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true)
    var id: Int=0,
    var text: String,
    var createdAt: Date
)
