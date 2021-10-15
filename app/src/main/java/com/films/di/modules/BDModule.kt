package com.films.di.modules

import com.films.data.bd.BDFilmsRepositoryService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class BDModule {
    @Singleton
    @Provides
    fun provideBDFilmsRepository(): BDFilmsRepositoryService {
        return BDFilmsRepositoryService()
    }
}