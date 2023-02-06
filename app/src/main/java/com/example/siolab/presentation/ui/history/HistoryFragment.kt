package com.example.siolab.presentation.ui.history

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.siolab.R
import com.example.siolab.databinding.FragmentHistoryBinding
import com.example.siolab.presentation.common.base.BaseFragment
import com.example.siolab.presentation.common.base.EventObserver
import com.example.siolab.presentation.common.extentions.showSnackBar
import com.example.siolab.presentation.ui.TestActivity
import com.example.siolab.presentation.ui.send.SendViewModel
import kotlinx.coroutines.launch

class HistoryFragment : BaseFragment<FragmentHistoryBinding>(R.layout.fragment_history) {
    private val historyViewModel: HistoryViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        observeData()
    }

    override fun onStart() {
        super.onStart()
        // observeData()
    }

    private fun initListener() {
        binding.historyTestSnackbarBtn.setOnClickListener {
            historyViewModel.userClickOnButton()
        }

        binding.historyTestNavigationBtn.setOnClickListener {
            val intent = Intent(activity, TestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun observeData() {
        historyViewModel.isToastEvent.observe(viewLifecycleOwner, EventObserver {
            if (it) binding.root.showSnackBar("클릭 이벤트 감지", isShort = true)
        })
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                historyViewModel.isToastFlowEvent.collect {
                    if (it) binding.root.showSnackBar("클릭 이벤트 감지", isShort = true)
                }
            }
        }
    }
}