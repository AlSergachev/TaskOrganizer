package com.example.taskorganizer.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.taskorganizer.domain.models.TaskModel

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskModel)

    @Delete
    suspend fun delete(task: TaskModel)

    @Query("SELECT * from task_table")
    fun getListTasks(): LiveData<List<TaskModel>>
}