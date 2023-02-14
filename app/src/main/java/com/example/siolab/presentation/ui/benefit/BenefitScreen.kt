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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

data class TestModel(var num: Int)

@SuppressLint("UnrememberedMutableState")
@Composable
fun BenefitScreen(
    modifier: Modifier = Modifier,
    // benefitViewModel: BenefitViewModel = viewModel(),
) {
    val benefitViewModel: BenefitViewModel = viewModel(LocalContext.current as ComponentActivity)
    val testNumber by benefitViewModel.testNumber.collectAsState()
    var number by mutableStateOf(0)


    Column(modifier.fillMaxWidth()) {
        Button(onClick = {
            // benefitViewModel.addNumber()
            number++
            Timber.e("!!!! $number !!!!")

        }) {
            Text("Add Count")
        }
        Text(text = number.toString())
        //CounterA(modifier = modifier, benefitViewModel = benefitViewModel)
        //CounterB(modifier = modifier, benefitViewModel = benefitViewModel)
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