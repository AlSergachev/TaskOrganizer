package com.example.taskorganizer.presentation.fragments.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.taskorganizer.domain.usecase.SortTaskListByUseCase
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val sortTaskListByUseCase: SortTaskListByUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ListViewModel(
            getTaskListUseCase = getTaskListUseCase,
            saveTaskUseCase = saveTaskUseCase,
            sortTaskListByUseCase = sortTaskListByUseCase
        ) as T
    }
}
