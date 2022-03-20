package com.example.taskorganizer.domain.models

import android.text.format.Time

data class TaskModel(
    var title: String = "",
    var description: String = "",
    var deadline: String = "",   // todo: toTime
    var reminder: Boolean = false,
    var place: String = "",
    var isDone: Boolean = false,
    var excuse: String = ""
)
