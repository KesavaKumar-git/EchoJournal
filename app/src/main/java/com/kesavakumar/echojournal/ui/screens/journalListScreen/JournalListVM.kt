package com.kesavakumar.echojournal.ui.screens.journalListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class JournalListVM @Inject constructor(
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
        _uiState.update {
            when(action)
            {
                is JournalListAction.SelectMood -> {
                    it.copy(selectedMoods = _uiState.value.selectedMoods + action.mood)
                }
                is JournalListAction.DeselectMood -> {
                    it.copy(selectedMoods = _uiState.value.selectedMoods - action.mood)
                }
                is JournalListAction.SelectTopic -> {
                    it.copy(selectedTopics = _uiState.value.selectedTopics + action.topic)
                }

                is JournalListAction.DeselectTopic -> {
                    it.copy(selectedTopics = _uiState.value.selectedTopics - action.topic)
                }

                JournalListAction.ClearMoodFilter -> {
                    it.copy(selectedMoods = emptySet())
                }

                JournalListAction.ClearTopicFilter -> {
                    it.copy(selectedTopics = emptySet())
                }

                is JournalListAction.CanShowSheetToRecord -> {

                    if (action.show)
                    {
                        it.copy(showSheetToRecord = true)
                    }
                    else
                    {
                        it.copy(showSheetToRecord = false, isRecording = false, isPaused = false, recordingTime = "")
                    }
                }

                JournalListAction.StartRecording -> {
                    it.copy(isRecording = true, isPaused = false)
                }

                JournalListAction.DiscardRecording -> {
                    it.copy(isRecording = false, isPaused = false, recordingTime = "")
                }

                JournalListAction.OnAddRecord -> {
                    it.copy(isRecording = false, showSheetToRecord = false)
                }

                JournalListAction.PauseRecording -> {
                    it.copy(isPaused = true, isRecording = false)
                }
            }
        }
    }

}