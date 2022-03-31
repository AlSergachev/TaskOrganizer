package com.example.taskorganizer.data.basedata.dao

import androidx.room.*
import com.example.taskorganizer.domain.models.TaskModel

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: TaskModel)

    @Delete
    fun delete(task: TaskModel)

    @Query("SELECT * from task_table")
    fun getListTasks(): List<TaskModel>

    @Query("SELECT * from task_table ORDER BY priority DESC")
    fun getListSortedByPriority(): List<TaskModel>

    @Query("SELECT * from task_table ORDER BY created DESC")
    fun getListSortedByCreated(): List<TaskModel>

    @Query("SELECT * from task_table WHERE deadline!=0 ORDER BY deadline ASC")
    fun getListSortedByDeadline(): List<TaskModel>
}