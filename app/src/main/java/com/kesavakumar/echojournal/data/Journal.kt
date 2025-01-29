package com.kesavakumar.echojournal.data

data class Journal(
    val id: Int,
    val title: String,
    val description: String,
    val mood: String,
    val timestamp: Long
)