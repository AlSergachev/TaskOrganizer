package com.example.taskorganizer.presentation.fragments.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase

class CreateViewModel : ViewModel() {

    private val taskLiveData = MutableLiveData<TaskModel>()
    val task: LiveData<TaskModel> get() = taskLiveData

    fun save(newTask: TaskModel) {
//        val isTrue = SaveTaskUseCase.execute(newTask)
    }


}