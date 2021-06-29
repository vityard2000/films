package com.films

import android.app.Application
import com.films.di.ApplicationComponent
import com.films.di.DaggerApplicationComponent

class FilmsApplication: Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.create()

    }
}