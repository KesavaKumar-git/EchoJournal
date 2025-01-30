package com.kesavakumar.echojournal.ui.screens.journalListScreen

import com.kesavakumar.echojournal.ui.modal.JournalUi
import com.kesavakumar.echojournal.ui.modal.Mood

data class JournalListState(
    val entries: Map<String, List<JournalUi>> = emptyMap(),
    val selectedMoods: Set<Mood> = emptySet(),
    val selectedTopics: Set<String> = emptySet(),
    val currentlyPlayingId: String? = null,
    val isRecording: Boolean = false,
    val showSheetToRecord: Boolean = false,
    val recordingTime: String = "",
    val isPaused: Boolean = false
)