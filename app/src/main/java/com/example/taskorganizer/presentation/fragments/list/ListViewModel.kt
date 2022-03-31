package com.example.taskorganizer.presentation.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SortTaskListByUseCase
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.presentation.utils.SortType

class ListViewModel(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
    private val sortTaskListByUseCase: SortTaskListByUseCase
) : ViewModel() {


    fun sortBy(sortType: SortType) {
        sortTaskListByUseCase.execute(sortType)
    }

    fun getList(): LiveData<List<TaskModel>> {
        return getTaskListUseCase.execute()
    }

    fun save(task: TaskModel): Boolean {
        return saveTaskUseCase.execute(task)
    }

}