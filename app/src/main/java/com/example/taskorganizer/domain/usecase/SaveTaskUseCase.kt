package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SaveTaskUseCase(
    private val taskRepository: TaskRepository
) {

    fun execute(task: TaskModel): Boolean {
        try {
            MainScope().launch(Dispatchers.IO) {
                taskRepository.insertTask(task)
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }
}