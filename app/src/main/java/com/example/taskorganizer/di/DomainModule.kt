package com.example.taskorganizer.di

import com.example.taskorganizer.domain.repository.ExcuseRepository
import com.example.taskorganizer.domain.repository.TaskRepository
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetTaskListUseCase(repository: TaskRepository): GetTaskListUseCase {
        return GetTaskListUseCase(repository = repository)
    }

    @Provides
    fun provideDeleteTaskUseCase(repository: TaskRepository): DeleteTaskUseCase {
        return DeleteTaskUseCase(repository = repository)
    }

    @Provides
    fun provideSaveTaskUseCase(
        taskRepository: TaskRepository,
        excuseRepository: ExcuseRepository
    ): SaveTaskUseCase {
        return SaveTaskUseCase(
            taskRepository = taskRepository,
            excuseRepository = excuseRepository
        )
    }
}