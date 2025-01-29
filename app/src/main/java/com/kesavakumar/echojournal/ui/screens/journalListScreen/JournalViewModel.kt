package com.kesavakumar.echojournal.ui.screens.journalListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kesavakumar.echojournal.domain.JournalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class JournalViewModel @Inject constructor(
//    private val repository: JournalRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(JournalListState())
    val uiState = _uiState
        .onStart {  }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            JournalListState()
        )

    fun onAction(action: JournalListAction)
    {

    }

}