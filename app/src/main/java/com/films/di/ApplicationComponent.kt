package com.films.di

import com.films.data.network.NetworkFilmsRepositoryService
import com.films.di.modules.AppModule
import com.films.di.modules.BDModule
import com.films.di.modules.FilmsModule
import com.films.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [FilmsModule::class, AppModule::class, NetworkModule::class, BDModule::class])
interface ApplicationComponent {
    fun getNetworkFilmsRepositoryService(): NetworkFilmsRepositoryService
}