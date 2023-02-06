package com.example.siolab.presentation.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.siolab.presentation.common.base.Event
import com.example.siolab.presentation.common.base.SingleLiveEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class HistoryViewModel: ViewModel() {

    private val _isToastEvent = MutableLiveData<Event<Boolean>>()
    val isToastEvent: LiveData<Event<Boolean>>
        get() = _isToastEvent

    private val _isToastFlowEvent = MutableStateFlow(false)
    val isToastFlowEvent: StateFlow<Boolean>
        get() = _isToastFlowEvent

    fun userClickOnButton() {
        // _isToastEvent.value = Event(true)
        _isToastFlowEvent.value = true
    }

    companion object {
        private const val TAG = "HistoryViewModel"
    }
}