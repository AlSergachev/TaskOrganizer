package com.example.taskorganizer.data.repository

import androidx.lifecycle.LiveData
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel

class TaskRepositoryImpl(private val taskDao: TaskDao): TaskRepository {
    override val listTasks: LiveData<List<TaskModel>>
        get() = taskDao.getListTasks()

    override suspend fun insertTask(task: TaskModel, onSuccess: () -> Unit) {
        taskDao.insert(task)
        onSuccess()
    }

    override suspend fun deleteTask(task: TaskModel, onSuccess: () -> Unit) {
        taskDao.delete(task)
        onSuccess()
    }
}