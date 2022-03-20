package com.example.taskorganizer.domain.models

import android.os.Parcelable
import android.text.format.Time
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TaskModel(
    var title: String = "",
    var description: String = "",
    var deadline: String = "",   // todo: toTime
    var isReminder: Boolean = false,
    var place: String = "",
    var isDone: Boolean = false,
    var excuse: String = ""
): Parcelable
