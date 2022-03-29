package com.example.taskorganizer.data.basedata.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.taskorganizer.data.basedata.dao.TaskDao
import com.example.taskorganizer.domain.models.TaskModel
import com.example.taskorganizer.domain.repository.TaskRepository
import com.example.taskorganizer.presentation.utils.SortType

class TaskRepositoryImpl(private val taskDao: TaskDao) : TaskRepository {

    private var taskList = MutableLiveData<List<TaskModel>>()
    override fun listTasks(): LiveData<List<TaskModel>> = taskList

    override fun insertTask(task: TaskModel) {
        taskDao.insert(task)
    }

    override fun deleteTask(task: TaskModel) {
        taskDao.delete(task)
    }

    override fun sortBy(sortType: SortType) {
        when(sortType){
            SortType.PRIORITY -> taskList.postValue(taskDao.getListSortedByPriority())
            else -> taskList.postValue(taskDao.getListTasks())
        }
    }
}