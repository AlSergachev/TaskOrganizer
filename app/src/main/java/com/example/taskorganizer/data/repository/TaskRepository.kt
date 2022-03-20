package com.example.taskorganizer.data.repository

import androidx.lifecycle.LiveData
import com.example.taskorganizer.domain.models.TaskModel

interface TaskRepository {
    val listTasks: LiveData<List<TaskModel>>
    suspend fun insertTask(task: TaskModel, onSuccess: () -> Unit)
    suspend fun deleteTask(task: TaskModel, onSuccess: () -> Unit)
}