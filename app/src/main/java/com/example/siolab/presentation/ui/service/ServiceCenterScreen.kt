package com.example.siolab.presentation.ui.service

import android.annotation.SuppressLint
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.*
import kotlin.random.Random

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyScreen(
    modifier: Modifier = Modifier,
) {
    //AnimatedBox()
    SnackDetail()
//    val number = remember { mutableStateOf(0) }
//
//    Column(modifier.fillMaxSize().padding(50.dp)) {
//        Button(onClick = { number.value += 1 }, modifier = Modifier.wrapContentSize()) {
//            Text("ADD!!")
//        }
//        // SimpleStatelessComposableWithState(number = number)
//        SimpleStatelessComposableWithValue(number = number.value)
//    }
}

@Composable
fun SimpleStatelessComposableWithState(
    number: State<Int>
) {
    Text(text = number.value.toString())
}

@Composable
fun SimpleStatelessComposableWithValue(
    number: Int
) {
    Text(text = number.toString())
}

@Composable
fun AnimatedBox() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Cyan,
        targetValue = Color.Magenta,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(color)
            .drawBehind { drawRect(color) }
    )
}

@Composable
fun SnackDetail() {
    Column(modifier = Modifier.fillMaxSize()) {
        val scroll = rememberScrollState()

        Title { scroll.value }
        Content(scroll = scroll)
    }
}

@Composable
private fun Title(scrollProvider: () -> Int) {
    Column(
        modifier = Modifier
            .offset { IntOffset(x = 0, y = scrollProvider()) }
    ) {
        Text("test")
    }
}

@Composable
fun Content(scroll: ScrollState) {

    fun randomColor() = Color(Random.nextInt(256), Random.nextInt(256), Random.nextInt(256))

    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scroll)) {
        repeat(10) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .background(randomColor())
            )
        }
    }
}