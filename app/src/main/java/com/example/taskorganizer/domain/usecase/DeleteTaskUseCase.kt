package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class DeleteTaskUseCase(private val repository: TaskRepository) {

    suspend fun execute(task: TaskModel): Boolean {

        repository.deleteTask(task)
        // TODO: Implement task deleting
        return true
    }

}