package com.example.siolab.presentation.ui.benefit

import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siolab.designsystem.MyTextButton
import com.example.siolab.presentation.ui.TestActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import kotlin.coroutines.CoroutineContext

data class TestModel(var num: Int)

@OptIn(ExperimentalLifecycleComposeApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun BenefitScreen(
    modifier: Modifier = Modifier,
    benefitViewModel: BenefitViewModel = viewModel(),
) {
    val testNumber by benefitViewModel.testNumber.collectAsState()
    var number by mutableStateOf(0)

    var balance by remember { mutableStateOf(0) }
    val transactions = listOf(1000, 500, 100, 200, 300, 400, 500, 1000)

    val countingFlow: Flow<Int> = flow {
        for (i in 1..10) {
            delay(1000)
            emit(i)
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val coroutineContext = coroutineScope.coroutineContext

    // val count by countingFlow.collectAsStateWithLifecycle(initialValue = 0)
    var count by remember { mutableStateOf(0) }
    // var count by mutableStateOf(0)

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 30.dp)
    ) {

        Text(text = "current : $count")

        MyTextButton(
            modifier = Modifier.wrapContentSize(),
            onClick = { onButtonClick(coroutineScope, countingFlow) },
            text = { Text(text = "Button") }
        )
    }

}

fun onButtonClick(coroutineScope: CoroutineScope, countingFlow: Flow<Int>) {
    var myCount = 0
    coroutineScope.launch {
        countingFlow.collect {
            myCount = it
        }
    }
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CounterA(
    modifier: Modifier = Modifier,
    benefitViewModel: BenefitViewModel
) {
    val number by benefitViewModel.testNumber.collectAsStateWithLifecycle()
    Text(text = number.toString())
}

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun CounterB(
    modifier: Modifier = Modifier,
    benefitViewModel: BenefitViewModel
) {
    val number by benefitViewModel.testNumber.collectAsStateWithLifecycle()
    Text(text = number.toString())
}

@Composable
fun Counter(modifier: Modifier = Modifier) {
    var num by remember { mutableStateOf(0) }
    var testModelA by remember { mutableStateOf(TestModel(0)) }
    var testModelB by remember { mutableStateOf(TestModel(0)) }
    var testModelC by remember { mutableStateOf(TestModel(0)) }

    // var testNumber by remember { mutableStateOf(TestModel(0).num) }

    Column(modifier.fillMaxWidth()) {
        Button(
            onClick = {
                testModelA = TestModel(num = ++testModelA.num)
                println("!!! click 이후 A ${testModelA.num} !!!")

                testModelB = TestModel(num = testModelB.num++)
                println("!!! click 이후 B ${testModelB.num} !!!")

//                testModelC = TestModel(num = testModelC.num + 1)
//                println("!!! click 이후 C ${testModelC.num} !!!")

            }, Modifier.wrapContentSize()
        ) {
            Text("Add Count")
        }

        Column {
            Text("num++ : ${testModelA.num}")
            Text("++num : ${testModelB.num}")
            // Text("num + 1: ${testModelC.num}")
        }

    }
}

@Preview
@Composable
fun BenefitFragmentPreview() {
    Surface {
        BenefitScreen(
            Modifier
                .fillMaxSize()
                .padding(20.dp)
        )
    }
}