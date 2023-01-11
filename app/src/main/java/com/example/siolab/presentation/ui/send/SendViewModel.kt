package com.example.siolab.presentation.ui.send

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SendViewModel: ViewModel() {
    private val _moneyOfSender: MutableStateFlow<Int> = MutableStateFlow(0)
    val moneyOfSender: StateFlow<Int>
        get() = _moneyOfSender

    private val _moneyOfReceiver: MutableStateFlow<Double> = MutableStateFlow(0.00)
    val moneyOfReceiver: StateFlow<Double>
        get() = _moneyOfReceiver
}