package com.example.taskorganizer.presentation.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(
    private val getTaskListUseCase: GetTaskListUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(
            getTaskListUseCase = getTaskListUseCase
        ) as T
    }
}
