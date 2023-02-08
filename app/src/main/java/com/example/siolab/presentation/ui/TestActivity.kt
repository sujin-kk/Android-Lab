package com.example.siolab.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.*
import com.example.siolab.R
import com.example.siolab.databinding.ActivityMainBinding
import com.example.siolab.databinding.ActivityTestBinding
import com.example.siolab.presentation.common.base.BaseActivity
import com.example.siolab.presentation.common.extentions.showSnackBar
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class TestActivity : BaseActivity<ActivityTestBinding>(R.layout.activity_test) {
    private val viewModel: TestViewModel by viewModels()
    private lateinit var testViewModel: TestViewModel

    private val countingFlow: Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)
            emit(i)
            Timber.tag(TAG).e("emitted value : $i")
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val userText = binding.testSnackbarBtn.text
        binding.testSnackbarBtn.text = "hi"
        outState.putCharSequence("userText", userText)

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val userContent = savedInstanceState.getCharSequence("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testViewModel = ViewModelProvider(owner = this)[TestViewModel::class.java]
    }

    private fun initListener() {
        binding.testSnackbarBtn.setOnClickListener {
            viewModel.userClickOnButton()
        }
    }

    private fun observeData() {
        viewModel.isToastEvent.observe(this, Observer {
            if (it) binding.root.showSnackBar("클릭 이벤트 감지", isShort = true)
        })
    }

    private fun testFlow() {
        lifecycleScope.launch {
            viewModel.stateFlow.collect {
                Timber.tag(TAG).d("state collected value : $it")
            }
        }

        lifecycleScope.launch {
            viewModel.sharedFlow.collect {
                Timber.tag(TAG).e("shared collected value : $it")
            }
        }
    }

    companion object {
        private const val TAG = "TestActivity"
    }
}