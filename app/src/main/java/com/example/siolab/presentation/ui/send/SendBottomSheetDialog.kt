package com.example.siolab.presentation.ui.send

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siolab.R
import com.example.siolab.presentation.common.components.SentbeFooterButton

@Composable
fun SendBottomSheet(onNext: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.send_money_bottom_label),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        WayToSendCheckBox(isCheck = true, isNow = false)
        WayToSendCheckBox(isCheck = false, isNow = true)

        SentbeFooterButton(onClick = onNext, buttonText = "다음")
    }
}

@Composable
fun WayToSendCheckBox(isCheck: Boolean, isNow: Boolean) {
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

    Surface() {
        Row() {
            Column() {
                Text(text = wayToSendLabel, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                Text(text = wayToSendDesc)
            }
        }
    }
}


@Preview
@Composable
private fun SendBottomSheetPreview() {
    Surface(color = colorResource(id = R.color.white)) {
        SendBottomSheet({})
    }
}