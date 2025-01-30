package com.kesavakumar.echojournal.ui.screens.journalListScreen

import com.kesavakumar.echojournal.ui.modal.Mood

sealed interface JournalListAction
{
    data class SelectMood(val mood: Mood): JournalListAction
    data class SelectTopic(val topic: String): JournalListAction

    data class DeselectMood(val mood: Mood): JournalListAction
    data class DeselectTopic(val topic: String): JournalListAction

    data object ClearMoodFilter: JournalListAction
    data object ClearTopicFilter: JournalListAction

    data object OnAddRecord: JournalListAction

    data object StartRecording: JournalListAction
    data object PauseRecording: JournalListAction
    data object DiscardRecording: JournalListAction

    data class CanShowSheetToRecord(val show: Boolean): JournalListAction

}