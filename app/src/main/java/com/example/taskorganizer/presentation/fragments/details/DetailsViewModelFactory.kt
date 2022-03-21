package com.example.taskorganizer.presentation.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailsViewModel(
            saveTaskUseCase = saveTaskUseCase,
            deleteTaskUseCase = deleteTaskUseCase
        ) as T
    }
}
