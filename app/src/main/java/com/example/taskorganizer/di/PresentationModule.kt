package com.example.taskorganizer.di

import android.content.Context
import com.example.taskorganizer.domain.usecase.*
import com.example.taskorganizer.presentation.fragments.create.CreateViewModelFactory
import com.example.taskorganizer.presentation.fragments.details.DetailsViewModelFactory
import com.example.taskorganizer.presentation.fragments.list.ListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideCreateViewModelFactory(
        saveTaskUseCase: SaveTaskUseCase,
        setDeadlineUseCase: SetDeadlineUseCase
    ): CreateViewModelFactory {
        return CreateViewModelFactory(
            saveTaskUseCase = saveTaskUseCase,
            setDeadlineUseCase = setDeadlineUseCase
        )
    }

    @Provides
    fun provideDetailsViewModelFactory(
        saveTaskUseCase: SaveTaskUseCase,
        deleteTaskUseCase: DeleteTaskUseCase,
        setDeadlineUseCase: SetDeadlineUseCase
    ): DetailsViewModelFactory {
        return DetailsViewModelFactory(
            saveTaskUseCase = saveTaskUseCase,
            deleteTaskUseCase = deleteTaskUseCase,
            setDeadlineUseCase = setDeadlineUseCase
        )
    }

    @Provides
    fun provideListViewModelFactory(
        getTaskListUseCase: GetTaskListUseCase,
        saveTaskUseCase: SaveTaskUseCase,
        sortTaskListByUseCase: SortTaskListByUseCase
    ): ListViewModelFactory {
        return ListViewModelFactory(
            getTaskListUseCase = getTaskListUseCase,
            saveTaskUseCase = saveTaskUseCase,
            sortTaskListByUseCase = sortTaskListByUseCase
        )
    }

}