package com.kesavakumar.echojournal.database.di

import com.kesavakumar.echojournal.database.EchoJournalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule
{
    @Provides
    fun providesJournalDao(database: EchoJournalDatabase) = database.journalDao()
}