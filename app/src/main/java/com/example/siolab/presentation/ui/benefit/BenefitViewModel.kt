package com.example.siolab.presentation.ui.benefit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class BenefitViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<BenefitUiState> = MutableStateFlow(BenefitUiState.Loading)
    val uiState: StateFlow<BenefitUiState>
        get() = _uiState

    private val _testNumber: MutableStateFlow<Int> = MutableStateFlow(0)
    val testNumber: StateFlow<Int>
        get() = _testNumber

    private val _testWords: MutableStateFlow<MutableList<String>> = MutableStateFlow(
        mutableListOf(
            "A", "B", "C", "D"
        )
    )
    val testWords: StateFlow<List<String>>
        get() = _testWords


    val countingFlow: Flow<Int> = flow { // cold flow
        for (i in 1..10) {
            delay(1_000)
            emit(i)
        }
    }

    val textFlow: Flow<String> = flow {
        val textList = listOf("a", "b", "c", "d", "e", "f", "g")

        for (text in textList) {
            delay(1_000)
            emit(text)
        }
    }

    val countingStateFlow: StateFlow<Int> = countingFlow
        .stateIn(
            scope = viewModelScope,
            initialValue = 0,
            started = SharingStarted.WhileSubscribed(5_000) // 마지막 구독자가 사라졌을 때 flow 구독 정지
        ) // hot flow

    fun addWord(word: String) {
         _testWords.value = _testWords.value.toMutableList().apply {
             add(word)
         }
    }

    fun addNumber() {
        _testNumber.value++
        Timber.tag("ViewModelTest").e("!!!! ${_testNumber.value} !!!!")
    }
}

sealed interface BenefitUiState {
    data class Success(val testWords: List<String>) : BenefitUiState
    object Error : BenefitUiState
    object Loading : BenefitUiState
}