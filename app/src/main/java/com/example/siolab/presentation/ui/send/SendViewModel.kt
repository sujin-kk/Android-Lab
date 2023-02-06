package com.example.siolab.presentation.ui.send

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import timber.log.Timber

class SendViewModel : ViewModel() {
    val curSender = MutableStateFlow("KOR")
    val curReceiver = MutableStateFlow("USD")
    val curExchangeRate = MutableStateFlow("1254.32")

    val moneyOfSender: MutableStateFlow<String> = MutableStateFlow("0")
    val moneyOfReceiver: MutableStateFlow<String> = MutableStateFlow("0.00")

    fun calculateMoneyOfReceiver(): String {
        return if (moneyOfSender.value.isNotEmpty()) (moneyOfSender.value.toDouble() / curExchangeRate.value.toDouble()).toString() else ""
    }


    fun calculateMoneyOfSender(): String {
        return if (moneyOfReceiver.value.isNotEmpty()) (moneyOfReceiver.value.toDouble() * curExchangeRate.value.toDouble()).toString() else ""
    }

    companion object {
        private const val TAG = "SendViewModel"
    }
}