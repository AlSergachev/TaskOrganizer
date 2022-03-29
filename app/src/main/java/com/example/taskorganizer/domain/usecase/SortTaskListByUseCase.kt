package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.repository.TaskRepository
import com.example.taskorganizer.presentation.utils.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class SortTaskListByUseCase(private val repository: TaskRepository) {

    fun execute(sortType: SortType) = MainScope().launch(Dispatchers.IO) {
        repository.sortBy(sortType)
    }
}