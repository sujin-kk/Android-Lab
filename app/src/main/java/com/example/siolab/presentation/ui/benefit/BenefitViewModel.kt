package com.example.siolab.presentation.ui.benefit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import timber.log.Timber

class BenefitViewModel: ViewModel() {
    private val _testNumber: MutableStateFlow<Int> = MutableStateFlow(0)
    val testNumber: StateFlow<Int>
        get() = _testNumber

    fun addNumber() {
        _testNumber.value++
        Timber.tag("ViewModelTest").e("!!!! ${_testNumber.value} !!!!")
    }
}