package com.example.taskorganizer.domain.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.text.DateFormat

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
    var deadline: Long = 0,

    @ColumnInfo
    var isReminder: Boolean = false,

    @ColumnInfo
    var place: String = "",

    @ColumnInfo
    var isDone: Boolean = false,

    @ColumnInfo
    var excuse: String = "",

    @ColumnInfo
    var priority: Int = 0,

    @ColumnInfo
    var created: Long = System.currentTimeMillis()
) : Parcelable {
    val createdDateFormatted: String
        get() = DateFormat.getDateTimeInstance().format(created)
}
