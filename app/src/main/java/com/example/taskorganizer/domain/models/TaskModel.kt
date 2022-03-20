package com.example.taskorganizer.domain.models

import android.os.Parcelable
import android.text.format.Time
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "task_table")
@Parcelize
data class TaskModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo
    var title: String = "",

    @ColumnInfo
    var description: String = "",

    @ColumnInfo
    var deadline: String = "",   // todo: toTime

    @ColumnInfo
    var isReminder: Boolean = false,

    @ColumnInfo
    var place: String = "",

    @ColumnInfo
    var isDone: Boolean = false,

    @ColumnInfo
    var excuse: String = ""
): Parcelable
