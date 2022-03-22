package com.example.taskorganizer.presentation.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    fun save(task: TaskModel): Boolean {
        return saveTaskUseCase.execute(task)
    }

    fun delete(task: TaskModel): Boolean {
        return deleteTaskUseCase.execute(task)
    }
}
