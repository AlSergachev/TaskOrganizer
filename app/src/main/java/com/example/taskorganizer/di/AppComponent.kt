package com.example.taskorganizer.di

import com.example.taskorganizer.data.basedata.TaskBaseData
import com.example.taskorganizer.data.basedata.dao.TaskDao
import com.example.taskorganizer.domain.repository.ExcuseRepository
import com.example.taskorganizer.domain.repository.TaskRepository
import com.example.taskorganizer.presentation.MainActivity
import com.example.taskorganizer.presentation.fragments.create.CreateFragment
import com.example.taskorganizer.presentation.fragments.details.DetailsFragment
import com.example.taskorganizer.presentation.fragments.list.ListFragment
import dagger.Component
import javax.inject.Singleton


@Component(modules = [PresentationModule::class, DomainModule::class, DataModule::class])
@Singleton
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun injectListFragment(listFragment: ListFragment)
    fun injectCreateFragment(createFragment: CreateFragment)
    fun injectDetailsFragment(detailsFragment: DetailsFragment)

//    val context: Application
    val dataBase : TaskBaseData
    val taskRepository: TaskRepository
    val excuseRepository: ExcuseRepository
    val dao: TaskDao
}