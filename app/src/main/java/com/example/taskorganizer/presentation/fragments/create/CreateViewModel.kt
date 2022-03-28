package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

class CreateViewModel(
    private val saveTaskUseCase: SaveTaskUseCase
) : ViewModel() {

    fun save(newTask: TaskModel): Boolean {
        return saveTaskUseCase.execute(newTask)
    }

    fun isEmpty(task: TaskModel):Boolean{
        return task.title == "" && task.description == ""
    }

}