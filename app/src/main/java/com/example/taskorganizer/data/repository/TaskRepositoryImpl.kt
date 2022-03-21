package com.example.taskorganizer.data.repository

import androidx.lifecycle.LiveData
import com.example.taskorganizer.data.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    override fun listTasks(): LiveData<List<TaskModel>> {
        return taskDao.getListTasks()
    }

    override fun insertTask(task: TaskModel) {
        taskDao.insert(task)
    }

    override fun deleteTask(task: TaskModel) {
        taskDao.delete(task)
    }
}