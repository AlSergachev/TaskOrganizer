package com.example.taskorganizer.di

import android.content.Context
import com.example.taskorganizer.domain.usecase.DeleteTaskUseCase
import com.example.taskorganizer.domain.usecase.SortTaskListByUseCase
import com.example.taskorganizer.domain.usecase.GetTaskListUseCase
import com.example.taskorganizer.domain.usecase.SaveTaskUseCase
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
    fun provideCreateViewModelFactory(saveTaskUseCase: SaveTaskUseCase): CreateViewModelFactory {
        return CreateViewModelFactory(saveTaskUseCase = saveTaskUseCase)
    }

    @Provides
    fun provideDetailsViewModelFactory(
        saveTaskUseCase: SaveTaskUseCase,
        deleteTaskUseCase: DeleteTaskUseCase
    ): DetailsViewModelFactory {
        return DetailsViewModelFactory(
            saveTaskUseCase = saveTaskUseCase, deleteTaskUseCase = deleteTaskUseCase
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