package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

class CreateViewModelFactory(
    private val saveTaskUseCase: SaveTaskUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateViewModel(
            saveTaskUseCase = saveTaskUseCase
        ) as T
    }


}
