package com.example.siolab.presentation.ui.benefit

import android.annotation.SuppressLint
import android.os.Parcelable
import android.webkit.WebViewClient
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.siolab.designsystem.MyTextButton
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.parcelize.Parcelize
import timber.log.Timber

@Parcelize
data class TestModel(val num: Int) : Parcelable

data class TestData(val num: Int)

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalAnimationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun BenefitScreen(
    modifier: Modifier = Modifier,
    viewModel: BenefitViewModel = viewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
) {
    val testNumber by viewModel.testNumber.collectAsState()
    var number by mutableStateOf(0)

    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val count by viewModel.countingFlow.collectAsStateWithLifecycle(initialValue = 0)
    val text by viewModel.textFlow.collectAsStateWithLifecycle(initialValue = "a")

    val isFiveOfCount by remember {
        derivedStateOf {
            count == 5
        }
    }

    var userTextValue: String by remember {
        mutableStateOf("")
    }

    var importantKeyword = remember { mutableStateListOf("A", "E", "F") }
    val myWords by viewModel.testWords.collectAsStateWithLifecycle()

    val importantWords by remember {
        derivedStateOf { myWords.filter { importantKeyword.contains(it) } }
    }

    val importantWords2 by remember(key1 = importantKeyword) {
        derivedStateOf {
            myWords.filter { importantKeyword.contains(it) }
        }
    }

    val importantWords3 = remember(key1 = myWords) {
        myWords.filter { importantKeyword.contains(it) }
    }

    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                println("!!!!! Debug !!!!! : Start")
            } else if (event == Lifecycle.Event.ON_STOP) {
                println("!!!!! Debug !!!!! : Stop")
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)
        println("!!!!! Debug !!!!! : Add Observer")
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
            println("!!!!! Debug !!!!! : Remove Observer")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(top = 50.dp)
        ) {

            TextField(
                value = userTextValue,
                onValueChange = { newText -> userTextValue = newText }
            )

            MyTextButton(
                modifier = Modifier.wrapContentSize(),
                onClick = {
                    viewModel.addWord(userTextValue)
                    // importantKeyword.add("B")
                    // println("!!!!! Debug !!!! ${importantKeyword.joinToString(", ")}")
                },
                text = { Text(text = "Add") },
                enabled = userTextValue.isNotBlank()
            )

            LazyColumn {
                item {
                    Text(text = "Current Words", fontSize = 20.sp)
                }

                items(myWords) { word ->
                    Text(word)
                }

                item {
                    Spacer(modifier = Modifier.height(50.dp))
                    Text(text = "Important Words", fontSize = 20.sp)
                }

                items(importantWords3) { word ->
                    Text(word)
                }
            }

        }

        SnackbarHost(hostState = snackBarState, modifier = Modifier.align(Alignment.BottomCenter))
    }

}

@ExperimentalAnimationApi
@Composable
fun TestSnapshotFlow() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val listState = rememberLazyListState()

        var showButtonSnapshot by remember {
            mutableStateOf(false)
        }

        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 2
            }
        }

        LazyColumn(state = listState) {
            items(1000) { index ->
                Text(text = "Item: $index")
            }
        }

        Column {
            AnimatedVisibility(showButtonSnapshot) {
                Button({}) {
                    Text("Row 1 and 2 hiding")
                }
            }
        }

        LaunchedEffect(listState) {
            snapshotFlow { listState.firstVisibleItemIndex }
                .map { index -> index > 2 }
                .distinctUntilChanged()
                .collect {
                    showButtonSnapshot = it
                }
        }
    }
}

fun onButtonClick(
    coroutineScope: CoroutineScope,
    snackbarState: SnackbarHostState,
) {

//    coroutineScope.launch {
//        snackbarState.showSnackbar(
//            message = "Hi~~",
//            actionLabel = "ok",
//            SnackbarDuration.Short
//        )
//    }

}

@Composable
fun Widget1(sharedVar: Int) {
    Text(text = "sharedVar: $sharedVar")
}

@Composable
fun Widget2(sharedVar: Int) {
    Text(text = "sharedVar: $sharedVar")
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

//    Column(modifier.fillMaxWidth()) {
//        Button(
//            onClick = {
//                testModelA = TestModel(num = ++testModelA.num)
//                println("!!! click 이후 A ${testModelA.num} !!!")
//
//                testModelB = TestModel(num = testModelB.num++)
//                println("!!! click 이후 B ${testModelB.num} !!!")
//
////                testModelC = TestModel(num = testModelC.num + 1)
////                println("!!! click 이후 C ${testModelC.num} !!!")
//
//            }, Modifier.wrapContentSize()
//        ) {
//            Text("Add Count")
//        }
//
//        Column {
//            Text("num++ : ${testModelA.num}")
//            Text("++num : ${testModelB.num}")
//            // Text("num + 1: ${testModelC.num}")
//        }
//
//    }
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