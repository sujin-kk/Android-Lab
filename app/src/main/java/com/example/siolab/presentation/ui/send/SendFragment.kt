package com.example.siolab.presentation.ui.send

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.siolab.R
import com.example.siolab.databinding.FragmentSendBinding
import com.example.siolab.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class SendFragment : BaseFragment<FragmentSendBinding>(R.layout.fragment_send) {
    private val sendViewModel: SendViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initBinding()
    }

    private fun initView() {
        // sender
        binding.sendSenderInputView.isSender = true
        binding.sendSenderInputView.moneyContent = sendViewModel._moneyOfSender

        // receiver
        binding.sendReceiverInputView.isSender = false
        binding.sendReceiverInputView.moneyContent = sendViewModel._moneyOfReceiver
    }

    private fun initBinding() {
        binding.sendSenderInputView.sendMoneyInputEt.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Timber.e("sender edit text : ${s.toString()}")

                lifecycleScope.launch {
                    sendViewModel._moneyOfSender.collectLatest {
                        Timber.e("money of sender flow : $it")
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }


}