package com.example.taskorganizer.domain.usecase

import androidx.lifecycle.viewModelScope
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class DeleteTaskUseCase(val repository: TaskRepository) {

    fun execute(task: TaskModel): Boolean {
        try {
            MainScope().launch(Dispatchers.IO) {
                repository.deleteTask(task)
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }
}