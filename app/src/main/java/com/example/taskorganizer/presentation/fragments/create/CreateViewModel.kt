package com.example.taskorganizer.presentation.fragments.create

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.presentation.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateViewModel(
    private val saveTaskUseCase: SaveTaskUseCase
) : ViewModel() {

    fun save(newTask: TaskModel) = viewModelScope.launch(Dispatchers.IO) {
        try {
            saveTaskUseCase.execute(newTask)
        } catch (e: Exception) {
        }
    }



//    fun save(newTask: TaskModel): Boolean {
//        Log.e(Constants.TAG, "CreateViewModel.save")
//        return try{viewModelScope.launch(Dispatchers.IO) {
//            saveTaskUseCase.execute(newTask)
//        }
//            true
//        }catch (e:Exception){
//            false
//        }
//
//
////        return try{viewModelScope.launch(Dispatchers.IO) {
////            saveTaskUseCase.execute(newTask)
////        }
////            true
////        }catch (e:Exception){
////            false
////        }
//    }


}