package com.example.taskorganizer.data.repository

import androidx.lifecycle.LiveData
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao: TaskDao): TaskRepository {
    override val listTasks: LiveData<List<TaskModel>>
        get() = taskDao.getListTasks()

    override suspend fun insertTask(task: TaskModel) {
        taskDao.insert(task)
    }

    override suspend fun deleteTask(task: TaskModel) {
        taskDao.delete(task)
    }
}