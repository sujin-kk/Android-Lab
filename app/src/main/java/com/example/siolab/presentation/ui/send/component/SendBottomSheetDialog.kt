package com.example.siolab.presentation.ui.send

import android.view.LayoutInflater
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.siolab.R
import com.example.siolab.presentation.common.components.SentbeFooterButton

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SendBottomSheet() {
    var skipHalfExpanded by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = skipHalfExpanded
    )

    ModalBottomSheetLayout(
        sheetContent = {
            Surface(color = colorResource(id = R.color.white), shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp)) {
                SendBottomSheetContent({})
            }
        },
        sheetState = bottomSheetState
    ) {

    }
}

@Composable
private fun SendBottomSheetContent(onNext: () -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 24.dp)) {
        Text(
            text = stringResource(id = R.string.send_money_bottom_label),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        WayToSendCheckCard(isCheck = true, isNow = false)
        WayToSendCheckCard(isCheck = false, isNow = true)

        Spacer(modifier = Modifier.size(20.dp))

        SentbeFooterButton(onClick = onNext, buttonText = "다음")
    }
}

@Composable
private fun WayToSendCheckCard(isCheck: Boolean, isNow: Boolean) {
    val wayToSendLabel =
        stringResource(
            id = if (isNow) R.string.send_money_bottom_now_label
            else R.string.send_money_bottom_later_label
        )

    val wayToSendDesc =
        stringResource(
            id = if (isNow) R.string.send_money_bottom_now_desc
            else R.string.send_money_bottom_later_desc
        )

    Surface(
        border = BorderStroke(0.5.dp, colorResource(R.color.gray_200)),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            if (isCheck) CheckedBox() else UnCheckedBox()

            Spacer(Modifier.size(8.dp))

            Column() {
                Text(text = wayToSendLabel, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(6.dp))
                Text(text = wayToSendDesc, modifier = Modifier.wrapContentSize())
            }
        }
    }
}

@Composable
private fun CheckedBox() {
    Surface(
        border = BorderStroke(0.5.dp, colorResource(id = R.color.main_blue)),
        shape = CircleShape,
        modifier = Modifier.size(size = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .background(color = colorResource(id = R.color.main_blue))
        )
    }
}

@Composable
private fun UnCheckedBox() {
    Surface(
        border = BorderStroke(0.5.dp, colorResource(id = R.color.gray_200)),
        shape = CircleShape,
        modifier = Modifier.size(size = 20.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = colorResource(id = R.color.white))
        )
    }
}

@Preview
@Composable
private fun SendBottomSheetPreview() {
    SendBottomSheet()
}