package com.example.taskorganizer.presentation.fragments.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.presentation.utils.Constants

class ListViewModel(
    private val getTaskListUseCase: GetTaskListUseCase
) : ViewModel() {

    fun getList():LiveData<List<TaskModel>>{
        return getTaskListUseCase.execute()
    }

}