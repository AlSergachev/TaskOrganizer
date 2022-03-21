package com.example.taskorganizer.app

import android.app.Application
import android.content.Context
import com.example.taskorganizer.data.basedata.TaskBaseData
import com.example.taskorganizer.di.AppComponent
import com.example.taskorganizer.di.DaggerAppComponent
import com.example.taskorganizer.di.PresentationModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .presentationModule(PresentationModule(context = this))
            .build()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }