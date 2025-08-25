package com.example.todo.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todo.model.Todo


@Dao
interface todoDAO {

    @Query("SELECT * FROM todo ORDER BY createdAt DESC")
    fun getAllData(): LiveData<List<Todo>>

    @Insert
    fun insertData(todo: Todo)

    @Query("DELETE FROM todo WHERE id = :id")
    fun deleteData(id:Int)

    @Query("SELECT * FROM todo WHERE id = :id")
    fun getTodoById(id: Int): LiveData<Todo>

    @Query("UPDATE todo SET text = :text WHERE id = :id")
    fun updateData(text: String, id: Int)

}