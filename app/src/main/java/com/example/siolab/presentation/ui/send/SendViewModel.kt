package com.example.siolab.presentation.ui.send

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SendViewModel: ViewModel() {
    val _moneyOfSender: MutableStateFlow<String> = MutableStateFlow("0")
//    val moneyOfSender: StateFlow<String>
//        get() = _moneyOfSender

    val _moneyOfReceiver: MutableStateFlow<String> = MutableStateFlow("0.00")
//    val moneyOfReceiver: StateFlow<String>
//        get() = _moneyOfReceiver

}