package com.example.taskorganizer.domain.usecase

import com.example.taskorganizer.domain.models.TaskModel

class SaveTaskUseCase {

    fun execute(task:TaskModel): Boolean {
        task.excuse = addExcuse()


        // TODO: Implement task saving
        return false
    }

    private fun addExcuse():String{
        // TODO: Implement excuse adding
        return ""
    }

}