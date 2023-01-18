package com.example.siolab.presentation.ui.send

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.siolab.R
import com.example.siolab.databinding.FragmentSendBinding
import com.example.siolab.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SendFragment : BaseFragment<FragmentSendBinding>(R.layout.fragment_send) {
    private val sendViewModel: SendViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        observeData()
    }

    private fun initView() {
        // sender
        binding.sendSenderInputView.isSender = true
        binding.sendSenderInputView.moneyContent = sendViewModel.moneyOfSender

        // receiver
        binding.sendReceiverInputView.isSender = false
        binding.sendReceiverInputView.moneyContent = sendViewModel.moneyOfReceiver

        // TODO : 양방향 데이터 바인딩,,,,,,,
        binding.sendSenderInputView.sendMoneyInputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendViewModel.moneyOfSender.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        binding.sendReceiverInputView.sendMoneyInputEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sendViewModel.moneyOfReceiver.value = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })

        // compose
        binding.sendBottomFooterLayout.apply {
            setViewCompositionStrategy(
                ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
            )
            setContent {
                Surface() {
                    Column() {
                        SendMoneyItem(isStandard = false, onSendClick = { onSendMoneyItemClick(false) }) // express
                        SendMoneyItem(isStandard = true, onSendClick = { onSendMoneyItemClick(true) }) // standard
                    }
                }
            }
        }
    }

    private fun onSendMoneyItemClick(isStandard: Boolean) {

    }

    private fun observeData() {
        lifecycleScope.launch {
            with(sendViewModel) {
                // 보내는 금액 입력 -> 받는 금액 변화
                moneyOfSender.collectLatest {
                    if (binding.sendSenderInputView.sendMoneyInputEt.isFocused) {
                        binding.sendReceiverInputView.sendMoneyInputEt.setText(sendViewModel.calculateMoneyOfReceiver())
                    }
                }

                // 받는 금액 입력 -> 보내는 금액 변화
                moneyOfReceiver.collectLatest {
                    if (binding.sendReceiverInputView.sendMoneyInputEt.isFocused) {
                        binding.sendSenderInputView.sendMoneyInputEt.setText(sendViewModel.calculateMoneyOfSender())
                    }
                }
            }
        }
    }
}