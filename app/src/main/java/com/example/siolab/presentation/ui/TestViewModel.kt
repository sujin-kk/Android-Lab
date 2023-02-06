package com.example.siolab.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
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

    init {
//        viewModelScope.launch {
//            updateData()
//        }

//        viewModelScope.launch {
//            Timber.tag(TAG).e("ViewModelScope Start")
//            launch {
//                val a = async {
//                    delay(1000)
//                    1
//                }
//                val b = a.await()
//                println(b)
//            }
//        }

        viewModelScope.launch {
             Timber.tag(TAG).e("1")

            launch {
                Timber.tag(TAG).e("2")
                Timber.tag(TAG).e("3")

            }
        }
    }

//    private suspend fun updateData() {
//        repeat(10) {
//            delay(500)
//            _stateFlow.value = it
//            Timber.tag(TAG).d("emitted value : $it")
//        }
//    }

    private suspend fun updateData() {
        repeat(5) {
            delay(500)
            _sharedFlow.emit(100)
            _stateFlow.value = 100
        }
    }

    companion object {
        private const val TAG = "SioFlowTest"
    }
}