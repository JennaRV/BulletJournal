package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.Repositories.RepoImplementation.AuthenticationRepositoryImpl
import com.example.rocketjournal.model.Repositories.RepoImplementation.ListsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindListsRepository(listsRepositoryImpl: ListsRepositoryImpl): ListsRepository

    @Binds
    abstract fun authenticationRepository(authenticationRepositoryImpl: AuthenticationRepositoryImpl): AuthenticationRepository

}