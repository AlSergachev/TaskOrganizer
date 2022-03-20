package com.example.taskorganizer.data.basedata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel
import kotlinx.android.parcel.Parcelize

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskBaseData : RoomDatabase() {
    abstract fun getTaskDao(): TaskDao

    @Suppress("SENSELESS_COMPARISON")
    companion object {
        private lateinit var database: TaskBaseData

        @Synchronized
        fun getInstance(context: Context):TaskBaseData{
            if(database == null){
                database = Room.databaseBuilder(context, TaskBaseData::class.java, "db").build()
            }
            return database
        }

    }

}