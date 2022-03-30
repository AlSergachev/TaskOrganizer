package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.domain.usecase.SetDeadlineUseCase

@Suppress("UNCHECKED_CAST")
class CreateViewModelFactory(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val setDeadlineUseCase: SetDeadlineUseCase,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreateViewModel(
            saveTaskUseCase = saveTaskUseCase,
            setDeadlineUseCase = setDeadlineUseCase
        ) as T
    }

}
