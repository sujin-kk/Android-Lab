package com.example.siolab.presentation.ui.send

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.siolab.R
import com.example.siolab.databinding.FragmentSendBinding
import com.example.siolab.presentation.common.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SendFragment : BaseFragment<FragmentSendBinding>(R.layout.fragment_send) {
    private val sendViewModel: SendViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initListener()
    }

    private fun initView() {
        // sender
        binding.sendSenderInputView.isSender = true

        // receiver
        binding.sendReceiverInputView.isSender = false
    }

    private fun initListener() {

    }


}