package com.films.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideDispatchers(): CoroutineDispatcher {
        return Dispatchers.IO
    }
}