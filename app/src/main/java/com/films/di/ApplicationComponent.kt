package com.films.di

import com.films.di.modules.FilmsModule
import com.films.domain.useCases.LoadFilmsUC
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FilmsModule::class])
interface ApplicationComponent {
    fun getLoadFilmsUC(): LoadFilmsUC
}