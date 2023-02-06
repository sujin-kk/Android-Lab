package com.example.siolab.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.siolab.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

class TestActivity : AppCompatActivity() {
    private val viewModel: TestViewModel by viewModels()

    private val countingFlow: Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)
            emit(i)
            Timber.tag(TAG).e("emitted value : $i")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        Timber.tag(TAG).e("화면 회전 마침")

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

    override fun onDestroy() {
        super.onDestroy()
        Timber.tag(TAG).e("화면 회전 시작")

    }

//    override fun onStart() {
//        super.onStart()
//        Timber.tag("SioFlowTest").e("다시 포그라운드로 진입")
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Timber.tag("SioFlowTest").e("홈 버튼을 눌러 백그라운드 상태")
//    }

    companion object {
        private const val TAG = "SioFlowTest"
    }
}