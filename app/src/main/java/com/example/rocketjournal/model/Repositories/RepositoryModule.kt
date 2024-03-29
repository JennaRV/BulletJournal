package com.example.rocketjournal.model.Repositories

import com.example.rocketjournal.model.Repositories.RepoImplementation.AuthenticationRepositoryImpl
import com.example.rocketjournal.model.Repositories.RepoImplementation.EventRepositoryImpl
import com.example.rocketjournal.model.Repositories.RepoImplementation.JournalEntryRepositoryImpl
import com.example.rocketjournal.model.Repositories.RepoImplementation.JournalRepositoryImpl
import com.example.rocketjournal.model.Repositories.RepoImplementation.ListsRepositoryImpl
import com.example.rocketjournal.model.Repositories.RepoImplementation.UserRepositoryImpl
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

    @Binds
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserReopsitory

    @Binds
    abstract fun bindJournalRepository(journalRepositoryImpl: JournalRepositoryImpl): JournalRepository

    @Binds
    abstract fun bindJournalEntriesRepository(journalEntryRepositoryImpl: JournalEntryRepositoryImpl): JournalEntryRepository

    @Binds
    abstract fun bindEventRepository(eventRepositoryImpl: EventRepositoryImpl): EventRepository
}