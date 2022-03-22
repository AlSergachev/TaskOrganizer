package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SaveTaskUseCase(val repository: TaskRepository) {

    fun execute(task: TaskModel): Boolean {
        task.excuse = addExcuse()

        try {
            MainScope().launch(Dispatchers.IO) {
                repository.insertTask(task)
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    private fun addExcuse(): String {
        // TODO: Implement excuse adding
        return "Some kind of excuse"
    }
}