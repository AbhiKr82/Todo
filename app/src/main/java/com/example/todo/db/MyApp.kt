package com.example.todo.db

import android.app.Application
import androidx.room.Room


class MyApp: Application()  {
    companion object {
        lateinit var database: todoDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            todoDatabase::class.java,
            "TODO_DATABASE"
        ).build()
    }
}