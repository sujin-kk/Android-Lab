package com.example.siolab.presentation.ui.send

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SendViewModel : ViewModel() {
    val curSender = MutableLiveData("KOR")
    val curReceiver = MutableLiveData("USD")
    val curExchangeRate = MutableLiveData("1254.32")

    val moneyOfSender: MutableLiveData<String> = MutableLiveData("0")
    val moneyOfReceiver: MutableLiveData<String> = MutableLiveData("0.00")

    fun calculateMoneyOfReceiver(): String {
        return if (moneyOfSender.value == null) {
            "0"
        } else {
            (moneyOfSender.value!!.toDouble() / curExchangeRate.value!!.toDouble()).toString()
        }
    }


    fun calculateMoneyOfSender(): String {
        return if (moneyOfReceiver.value == null) {
            "0"
        } else {
            (moneyOfReceiver.value!!.toDouble() * curExchangeRate.value!!.toDouble()).toString()
        }
    }
}