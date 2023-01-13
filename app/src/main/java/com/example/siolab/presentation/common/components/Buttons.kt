package com.example.siolab.presentation.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.siolab.R

@Composable
fun SentbeFooterButton(onClick: () -> Unit, buttonText: String) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(6.dp),
        color = colorResource(id = R.color.main_blue)
    ) {
        Text(
            text = buttonText,
            color = colorResource(id = R.color.white),
            modifier = Modifier.padding(vertical = 16.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
private fun SentbeFooterButtonPreview() {
    SentbeFooterButton(onClick = { /*TODO*/ }, buttonText = "다음")
}