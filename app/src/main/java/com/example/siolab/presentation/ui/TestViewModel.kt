package com.example.siolab.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import timber.log.Timber


class TestViewModel : ViewModel() {
    private val _stateFlow = MutableStateFlow(99)
    val stateFlow: MutableStateFlow<Int>
        get() = _stateFlow

    private val _sharedFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val sharedFlow: MutableSharedFlow<Int>
        get() = _sharedFlow

    private val _isToastEvent = MutableLiveData<Boolean>()
    val isToastEvent: LiveData<Boolean>
        get() = _isToastEvent

    fun userClickOnButton() {
        _isToastEvent.value = true
    }

    private suspend fun updateData() {
        repeat(5) {
            delay(500)
            _sharedFlow.emit(100)
            _stateFlow.value = 100
        }
    }

    companion object {
        private const val TAG = "TestActivity"
    }
}