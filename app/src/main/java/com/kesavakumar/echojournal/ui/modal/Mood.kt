package com.kesavakumar.echojournal.ui.modal

import androidx.compose.ui.graphics.Color
import com.kesavakumar.echojournal.R

enum class Mood { Stressed, Sad, Neutral, Peaceful, Excited }

fun Mood.getMoodColor(): Color {
    return when (this) {
        Mood.Stressed -> Color(0xFFDE3A3A)
        Mood.Sad -> Color(0xFF3A8EDE)
        Mood.Neutral -> Color(0xFF41B278)
        Mood.Peaceful -> Color(0xFFBE3294)
        Mood.Excited -> Color(0xFFDB6C0B)
    }
}

fun Mood.getMoodIconResId(): Int {
    return when (this) {
        Mood.Stressed -> R.drawable.ic_stressed_mood
        Mood.Sad -> R.drawable.ic_sad_mood
        Mood.Neutral -> R.drawable.ic_neutral_mood
        Mood.Peaceful -> R.drawable.ic_peaceful_mood
        Mood.Excited -> R.drawable.ic_excited_mood
    }
}

fun Mood.getMoodBgColor(): Color
{
    return when (this) {
        Mood.Stressed -> Color(0xFFF8EFEF)
        Mood.Sad -> Color(0xFFEFF4F8)
        Mood.Neutral -> Color(0xFFEEF7F3)
        Mood.Peaceful -> Color(0xFFF6F2F5)
        Mood.Excited -> Color(0xFFF5F2EF)
    }
}