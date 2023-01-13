package com.example.siolab.presentation.ui.send

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

        WayToSendCheckCard(isCheck = true, isNow = false)
        WayToSendCheckCard(isCheck = false, isNow = true)

        SentbeFooterButton(onClick = onNext, buttonText = "다음")
    }
}

@Composable
fun WayToSendCheckCard(isCheck: Boolean, isNow: Boolean) {
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
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (isCheck) CheckedBox() else UnCheckedBox()

            Column() {
                Text(text = wayToSendLabel, fontSize = 16.sp, fontWeight = FontWeight.Bold)
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
    Surface(color = colorResource(id = R.color.white)) {
        SendBottomSheet({})
    }
}