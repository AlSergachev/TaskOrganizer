package com.example.taskorganizer.data.basedata

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.presentation.utils.Constants

@Database(entities = [TaskModel::class], version = 1, exportSchema = false)
abstract class TaskBaseData : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    @Suppress("SENSELESS_COMPARISON")
    companion object {
        private var database: TaskBaseData? = null

        @Synchronized
        fun getInstance(context: Context):TaskBaseData{
            if(database == null){
                database = Room.databaseBuilder(context, TaskBaseData::class.java, "db").build()
            }
            return database as TaskBaseData
        }

    }

}