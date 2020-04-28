package com.fernandocejas.sample.core.di.interactor

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.*
import javax.inject.Named

@Module
class UseCaseModule {
    @Named("UseCaseDispatcher")
    @Provides
    fun provideCoroutineDispatcher() :
            CoroutineDispatcher = Dispatchers.IO
}