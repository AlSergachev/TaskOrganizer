package com.example.taskorganizer.di

import android.content.Context
import com.example.taskorganizer.data.basedata.TaskBaseData
import com.example.taskorganizer.data.basedata.dao.TaskDao
import com.example.taskorganizer.data.basedata.repository.TaskRepositoryImpl
import com.example.taskorganizer.data.retrofit.repository.ExcuseRepositoryImpl
import com.example.taskorganizer.domain.repository.ExcuseRepository
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

    @Provides @Singleton
    fun provideTaskRepository(dao: TaskDao):TaskRepository{
        return TaskRepositoryImpl(taskDao = dao)
    }

    @Provides @Singleton
    fun provideExcuseRepository():ExcuseRepository{
        return ExcuseRepositoryImpl()
    }



}