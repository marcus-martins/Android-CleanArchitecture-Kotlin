package com.fernandocejas.sample.core.di.interactor

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.*
import javax.inject.Named

@Module
class UseCaseModule {
    @Named("UseCaseScope")
    @Provides
    fun provideCoroutineScope() =
            CoroutineScope(
                    SupervisorJob() + Dispatchers.Main
            )

    @Named("UseCaseDispatcher")
    @Provides
    fun provideCoroutineDispatcher() :
            CoroutineDispatcher = Dispatchers.IO
}