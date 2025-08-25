package com.example.todo.db

import androidx.room.TypeConverter
import java.util.Date

class Convertors {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }

}
