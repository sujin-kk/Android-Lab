package com.example.siolab.presentation.ui.service

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.siolab.R
import com.example.siolab.databinding.FragmentServiceCenterBinding
import com.example.siolab.presentation.common.base.BaseFragment
import com.example.siolab.presentation.ui.benefit.BenefitScreen

class ServiceCenterFragment : BaseFragment<FragmentServiceCenterBinding>(R.layout.fragment_service_center) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.serviceCv.apply {
            setContent {
                MyScreen(modifier = Modifier)
            }
        }
    }
}