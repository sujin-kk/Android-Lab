package com.example.siolab.presentation.ui.send

import androidx.lifecycle.ViewModel
import com.example.siolab.domain.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class SendViewModel @Inject constructor(
    private val localRepositoryA: LocalRepository,
    private val localRepositoryB: LocalRepository,
): ViewModel() {

    init {
        Timber.tag("SendViewModel").e(
            "repository A: ${localRepositoryA.hashCode()}" +
                    "\nrepository B: ${localRepositoryB.hashCode()}"
        )
    }

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