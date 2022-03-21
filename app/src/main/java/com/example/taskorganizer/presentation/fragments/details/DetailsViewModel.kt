package com.example.taskorganizer.presentation.fragments.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

    fun save(newTask: TaskModel): Boolean {
        return try{viewModelScope.launch(Dispatchers.IO) {
            saveTaskUseCase.execute(newTask)
        }
            true
        }catch (e:Exception){
            false
        }
    }

    fun delete(newTask: TaskModel): Boolean {
        return try{viewModelScope.launch(Dispatchers.IO) {
            deleteTaskUseCase.execute(newTask)
        }
            true
        }catch (e:Exception){
            false
        }
    }

}
