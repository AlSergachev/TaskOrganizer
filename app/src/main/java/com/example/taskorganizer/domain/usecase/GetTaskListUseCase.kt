package com.example.taskorganizer.domain.usecase

import androidx.lifecycle.LiveData
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository

class GetTaskListUseCase(private val repository: TaskRepository) {

    fun execute(): LiveData<List<TaskModel>> {
        return  repository.listTasks()
    }

}