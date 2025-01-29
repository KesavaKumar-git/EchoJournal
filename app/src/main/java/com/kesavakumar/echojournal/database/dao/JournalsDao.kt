package com.kesavakumar.echojournal.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kesavakumar.echojournal.database.table_entities.JournalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao
{
//    @Query(value = "SELECT * FROM echo_journals ORDER BY eventTimeMillis ASC")
//    fun getJournalsList(): Flow<List<JournalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journal: JournalEntity)
}

