package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.ExcuseRepository
import com.example.taskorganizer.domain.repository.TaskRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveTaskUseCase(
    private val taskRepository: TaskRepository,
    private val excuseRepository: ExcuseRepository
) {

    fun execute(task: TaskModel): Boolean {

        try {
            MainScope().launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    if (task.excuse == "") task.excuse = addExcuse()
                }
                taskRepository.insertTask(task)
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    private suspend fun addExcuse(): String {
        return excuseRepository.getExcuse().body()?.get(0)?.excuse.toString()
    }

}