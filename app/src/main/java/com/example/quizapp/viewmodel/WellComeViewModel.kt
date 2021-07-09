package com.example.quizapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class WellComeViewModel : ViewModel() {

    private val wellComeEventChannelFlow = Channel<WellComeEvent>()
    val wellComeEvent = wellComeEventChannelFlow.receiveAsFlow()

    fun onPlayGameClick() = viewModelScope.launch {
        wellComeEventChannelFlow.send(WellComeEvent.NavigateToPlayGameScreen)
    }

    fun onShowScoreClick() = viewModelScope.launch {
        wellComeEventChannelFlow.send(WellComeEvent.NavigateToShowScoreScreen)
    }

    sealed class WellComeEvent {
        object NavigateToPlayGameScreen : WellComeEvent()
        object NavigateToShowScoreScreen : WellComeEvent()
    }


}