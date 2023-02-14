package com.example.siolab.di

import com.example.siolab.data.local.LocalRepositoryImpl
import com.example.siolab.domain.local.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindsLocalRepository(
        localRepositoryImpl: LocalRepositoryImpl
    ): LocalRepository
}