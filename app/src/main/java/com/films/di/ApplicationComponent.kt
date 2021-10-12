package com.films.di

import com.films.di.modules.AppModule
import com.films.di.modules.FilmsModule
import com.films.di.modules.NetworkModule
import com.films.domain.useCases.LoadFilmsUC
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FilmsModule::class, AppModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun getLoadFilmsUC(): LoadFilmsUC
}