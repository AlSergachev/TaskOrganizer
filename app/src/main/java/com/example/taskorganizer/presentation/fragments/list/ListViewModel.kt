package com.example.taskorganizer.presentation.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

class ListViewModel(
    private val getTaskListUseCase: GetTaskListUseCase,
    private val saveTaskUseCase: SaveTaskUseCase,
) : ViewModel() {

    fun getList():LiveData<List<TaskModel>>{
        return getTaskListUseCase.execute()
    }

    fun save(task: TaskModel): Boolean {
        return saveTaskUseCase.execute(task)
    }

}