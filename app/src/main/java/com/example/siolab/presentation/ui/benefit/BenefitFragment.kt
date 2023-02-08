package com.example.siolab.presentation.ui.benefit

import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.siolab.R
import com.example.siolab.databinding.FragmentBenefitBinding
import com.example.siolab.presentation.common.base.BaseFragment

class BenefitFragment : BaseFragment<FragmentBenefitBinding>(R.layout.fragment_benefit) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        binding.benefitCv.apply {
            setContent {
                BenefitScreen(modifier = Modifier.padding(20.dp))
            }
        }
    }


}