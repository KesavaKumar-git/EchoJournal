package com.kesavakumar.echojournal.ui.screens.journalListScreen

import com.kesavakumar.echojournal.ui.modal.Mood

sealed interface JournalListAction
{
    data class SelectMood(val mood: Mood): JournalListAction
    data class SelectTopic(val topic: String): JournalListAction

    data object ClearMoodFilter: JournalListAction
    data object ClearTopicFilter: JournalListAction
}