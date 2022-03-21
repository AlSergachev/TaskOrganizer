package com.example.taskorganizer.presentation.fragments.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase

class ListViewModel(
    private val getTaskListUseCase: GetTaskListUseCase
) : ViewModel() {

//    private val taskListLiveData = MutableLiveData<List<TaskModel>>()
    private val taskListLiveData = getTaskListUseCase.execute()
    val taskList: LiveData<List<TaskModel>> get() = taskListLiveData

//    fun getList():LiveData<List<TaskModel>>{
//        return getTaskListUseCase.execute()
//    }


}