package com.kesavakumar.echojournal.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kesavakumar.echojournal.database.dao.JournalDao
import com.kesavakumar.echojournal.database.table_entities.JournalEntity


const val DB_VERSION = 1

@Database(
    entities = [JournalEntity::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class EchoJournalDatabase: RoomDatabase()
{
    abstract fun journalDao(): JournalDao
}