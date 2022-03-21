package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class DeleteTaskUseCase(val repository: TaskRepository) {

    suspend fun execute(task: TaskModel): Boolean {
        return try {
            repository.deleteTask(task)
            true
        } catch (e: Exception) {
            true
        }
    }
}