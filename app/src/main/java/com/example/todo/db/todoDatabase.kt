package com.example.todo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todo.model.Todo


@Database(entities = [Todo::class], version = 1)
@TypeConverters(Convertors::class)
abstract class todoDatabase: RoomDatabase() {

    abstract fun todoDao(): todoDAO

}