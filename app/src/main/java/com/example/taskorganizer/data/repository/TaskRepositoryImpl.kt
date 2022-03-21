package com.example.taskorganizer.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository
import com.example.taskorganizer.presentation.Constants

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {
//    override val listTasks: LiveData<List<TaskModel>>
//        get() = taskDao.getListTasks()

    override fun listTasks(): LiveData<List<TaskModel>> {
        Log.e(Constants.TAG, "TaskRepositoryImpl.getListTasks")
        return taskDao.getListTasks()

    }


    override fun insertTask(task: TaskModel) {
        taskDao.insert(task)
        Log.e(Constants.TAG, "TaskRepositoryImpl.insertTask")
    }

    override suspend fun deleteTask(task: TaskModel) {
        taskDao.delete(task)
    }
}