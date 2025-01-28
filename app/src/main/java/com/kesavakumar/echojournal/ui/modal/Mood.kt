package com.kesavakumar.echojournal.ui.modal

import androidx.compose.ui.graphics.Color
import com.kesavakumar.echojournal.R

enum class Mood{ STRESSED, SAD, NEUTRAL, PEACEFUL, EXCITED }

fun Mood.getMoodColor(): Color {
    return when (this) {
        Mood.STRESSED -> Color(0xFFDE3A3A)
        Mood.SAD -> Color(0xFF3A8EDE)
        Mood.NEUTRAL -> Color(0xFF41B278)
        Mood.PEACEFUL -> Color(0xFFBE3294)
        Mood.EXCITED -> Color(0xFFDB6C0B)
    }
}

fun Mood.getMoodIconResId(): Int {
    return when (this) {
        Mood.STRESSED -> R.drawable.ic_stressed_mood
        Mood.SAD -> R.drawable.ic_sad_mood
        Mood.NEUTRAL -> R.drawable.ic_neutral_mood
        Mood.PEACEFUL -> R.drawable.ic_peaceful_mood
        Mood.EXCITED -> R.drawable.ic_excited_mood
    }
}

fun Mood.getMoodBgColor(): Color
{
    return when (this) {
        Mood.STRESSED -> Color(0xFFF8EFEF)
        Mood.SAD -> Color(0xFFEFF4F8)
        Mood.NEUTRAL -> Color(0xFFEEF7F3)
        Mood.PEACEFUL -> Color(0xFFF6F2F5)
        Mood.EXCITED -> Color(0xFFF5F2EF)
    }
}