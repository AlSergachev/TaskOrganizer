package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class SaveTaskUseCase(private val repository: TaskRepository) {

    suspend fun execute(task: TaskModel): Boolean {
        task.excuse = addExcuse()
        repository.insertTask(task)

        // TODO: Implement task saving
        return false
    }

    private fun addExcuse(): String {
        // TODO: Implement excuse adding
        return ""
    }

}