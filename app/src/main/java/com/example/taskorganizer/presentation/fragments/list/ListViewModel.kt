package com.example.taskorganizer.presentation.fragments.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.presentation.Constants

class ListViewModel(
    private val getTaskListUseCase: GetTaskListUseCase
) : ViewModel() {

//    private val taskListLiveData = MutableLiveData<List<TaskModel>>()
//    private val taskListLiveData = getTaskListUseCase.execute()
//    val taskList: LiveData<List<TaskModel>> get() = taskListLiveData
//
//    fun getList():LiveData<List<TaskModel>>{
//        return taskList
//    }

    private var liveList: LiveData<List<TaskModel>>? = null

    fun getList():LiveData<List<TaskModel>>{
        Log.e(Constants.TAG, "ListViewModel.getList.before $liveList")
        liveList =  getTaskListUseCase.execute()
        Log.e(Constants.TAG, "ListViewModel.getList.after $liveList")
        return liveList as LiveData<List<TaskModel>>
    }



}