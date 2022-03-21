package com.example.taskorganizer.domain.repository

import androidx.lifecycle.LiveData
import com.example.taskorganizer.domain.models.TaskModel

interface TaskRepository {
    val listTasks: LiveData<List<TaskModel>>
    fun insertTask(task: TaskModel)
    suspend fun deleteTask(task: TaskModel)
}