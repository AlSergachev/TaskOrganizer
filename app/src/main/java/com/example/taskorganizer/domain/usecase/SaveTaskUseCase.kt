package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class SaveTaskUseCase(val repository: TaskRepository) {

    fun execute(task: TaskModel): Boolean {
        task.excuse = addExcuse()

        return try {
            repository.insertTask(task)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun addExcuse(): String {
        // TODO: Implement excuse adding
        return "Some kind of excuse"
    }
}