package com.example.taskorganizer.di

import android.content.Context
import androidx.room.Database
import com.example.taskorganizer.data.basedata.TaskBaseData
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.data.repository.TaskRepositoryImpl
import com.example.taskorganizer.domain.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides @Singleton
    fun provideTaskDateBase(context: Context):TaskBaseData{
       return TaskBaseData.getInstance(context = context)
    }

    @Provides @Singleton
    fun provideTaskDao(database: TaskBaseData):TaskDao{
        return database.getTaskDao()
    }

    @Provides
    fun provideTaskRepository(dao: TaskDao):TaskRepository{
        return TaskRepositoryImpl(taskDao = dao)
    }


}