package com.films.di.modules

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
class AppModule {

    //Data dispatcher
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.IO
    }

    //scope с ЖЦ всего приложения
    @Singleton
    @Provides

    fun provideCoroutineScope(dispatcher: CoroutineDispatcher): CoroutineScope {
        return CoroutineScope(SupervisorJob() + dispatcher)
    }
}