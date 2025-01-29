package com.kesavakumar.echojournal.database.di

import android.content.Context
import androidx.room.Room
import com.kesavakumar.echojournal.database.EchoJournalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesEchoJournalDatabase(@ApplicationContext context: Context) = Room
        .databaseBuilder(context.applicationContext, EchoJournalDatabase::class.java, "EchoJournalDB")
        .fallbackToDestructiveMigration()
        .build()
}
