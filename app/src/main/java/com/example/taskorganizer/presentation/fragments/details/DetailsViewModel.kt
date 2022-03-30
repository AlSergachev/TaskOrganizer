package com.example.taskorganizer.presentation.fragments.details

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import com.example.taskorganizer.domain.usecase.SetDeadlineUseCase
import java.util.*

class DetailsViewModel(
    private val saveTaskUseCase: SaveTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val setDeadlineUseCase: SetDeadlineUseCase,
) : ViewModel() {

    fun save(task: TaskModel): Boolean {
        return saveTaskUseCase.execute(task)
    }

    fun delete(task: TaskModel): Boolean {
        return deleteTaskUseCase.execute(task)
    }

    fun setDeadline(context: Context, saveDeadlineCallback: (Calendar) -> Unit) {
        return setDeadlineUseCase.execute(
            context = context,
            saveDeadlineCallback = saveDeadlineCallback
        )
    }
}