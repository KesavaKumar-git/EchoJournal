package com.kesavakumar.echojournal.ui.modal

data class JournalUi(
    val id: Int,
    val title: String,
    val description: String,
    val selectedMood: Mood,
    val date: String,
    val time: String,
    val topics: List<String>
)