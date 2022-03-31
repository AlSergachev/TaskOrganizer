package com.example.taskorganizer.di

import com.example.taskorganizer.domain.repository.ExcuseRepository
import com.example.taskorganizer.domain.repository.TaskRepository
import com.example.taskorganizer.domain.usecase.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetTaskListUseCase(repository: TaskRepository): GetTaskListUseCase {
        return GetTaskListUseCase(repository = repository)
    }

    @Provides
    fun provideSetDeadlineUseCase(): SetDeadlineUseCase {
        return SetDeadlineUseCase()
    }

    @Provides
    fun provideSortTaskListByUseCase(repository: TaskRepository): SortTaskListByUseCase {
        return SortTaskListByUseCase(repository = repository)
    }

    @Provides
    fun provideDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository = repository)
    }

    @Provides
    fun provideSaveTaskUseCase(taskRepository: TaskRepository): SaveTaskUseCase {
        return SaveTaskUseCase(taskRepository = taskRepository)
    }
}