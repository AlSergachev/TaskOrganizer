package com.example.taskorganizer.presentation.fragments.create

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.domain.usecase.SetDeadlineUseCase
import java.util.*

class CreateViewModel(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val setDeadlineUseCase: SetDeadlineUseCase,
) : ViewModel() {

    fun save(newTask: TaskModel): Boolean {
        return saveTaskUseCase.execute(newTask)
    }

    fun isEmpty(task: TaskModel): Boolean {
        return task.title == "" && task.description == ""
    }

    fun setDeadline(context: Context, saveDeadlineCallback: (Calendar) -> Unit) {
        return setDeadlineUseCase.execute(
            context = context,
            saveDeadlineCallback = saveDeadlineCallback
        )
    }

}


