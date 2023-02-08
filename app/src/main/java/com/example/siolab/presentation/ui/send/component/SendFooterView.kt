package com.example.siolab.presentation.ui.send.component

import androidx.activity.ComponentActivity
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.siolab.R
import com.example.siolab.presentation.ui.send.SendViewModel


@Composable
private fun ArrivalTime() {
    Column(
        Modifier
            .wrapContentSize()
    ) {
        Text(
            text = stringResource(id = R.string.send_money_bank_label),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "2023. 1. 16. 도착", color = colorResource(id = R.color.gray_200)
        )
    }
}

@Composable
private fun WayToSend(isStandard: Boolean) {
    val backgroundColor = if (isStandard) R.color.background_blue else R.color.background_red
    val highlightColor = if (isStandard) R.color.main_blue else R.color.highlight_red
    val timeIcon = if (isStandard) R.drawable.ic_time_standard else R.drawable.ic_time_express
    val wayText =
        if (isStandard) R.string.send_money_way_standard else R.string.send_money_way_express

    Surface(
        color = colorResource(id = backgroundColor),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
        ) {
            Image(
                painter = painterResource(id = timeIcon),
                contentDescription = "way to send",
                Modifier.size(width = 14.dp, height = 14.dp)
            )
            Text(
                text = stringResource(id = wayText),
                color = colorResource(id = highlightColor),
                modifier = Modifier.padding(start = 4.dp),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
private fun Fee(isStandard: Boolean) {
    val feeContent = if (isStandard) "2,500 KRW" else "5,000 KRW"

    Column(horizontalAlignment = Alignment.End) {
        Text(
            text = stringResource(id = R.string.send_money_fee_label),
            color = colorResource(id = R.color.main_blue),
            fontSize = 14.sp
        )
        Text(
            text = feeContent,
            color = colorResource(id = R.color.main_blue),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun Detail() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = stringResource(id = R.string.send_money_detail_label),
            color = colorResource(id = R.color.gray_200),
            fontSize = 12.sp
        )
        Image(
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "detail arrow",
            Modifier.size(width = 18.dp, height = 18.dp)
        )
    }
}

@Composable
fun SendMoneyItem(isStandard: Boolean, onSendClick: () -> Unit) {
    Surface(
        color = colorResource(id = R.color.white),
        border = BorderStroke(0.5.dp, colorResource(id = R.color.gray_200)),
        shape = RoundedCornerShape(6.dp),
        shadowElevation = 8.dp,
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 24.dp)
            .fillMaxWidth()
            .clickable(onClick = onSendClick)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ArrivalTime()
                WayToSend(isStandard = isStandard)
            }
            Spacer(
                Modifier
                    .size(20.dp)
                    .fillMaxWidth())
            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Detail()
                Fee(isStandard = isStandard)
            }
        }
    }
}

@Preview
@Composable
private fun SendMoneyItemPreview() {
    SendMoneyItem(true, { })
}