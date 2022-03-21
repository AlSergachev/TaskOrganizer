package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateViewModel(
    private val saveTaskUseCase: SaveTaskUseCase
) : ViewModel() {

    private val emptyTask = TaskModel()

    fun save(newTask: TaskModel): Boolean {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                saveTaskUseCase.execute(newTask)
            }
        } catch (e: Exception) {
            return false
        }
        return true
    }

    fun isEmpty(task: TaskModel) = task == emptyTask

}