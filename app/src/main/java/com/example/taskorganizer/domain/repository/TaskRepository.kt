package com.example.taskorganizer.domain.repository

import androidx.lifecycle.LiveData
import com.example.taskorganizer.domain.models.TaskModel

interface TaskRepository {
    fun listTasks(): LiveData<List<TaskModel>>
    fun insertTask(task: TaskModel)
    fun deleteTask(task: TaskModel)
}