package com.ps.presentation.features.actordetail.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ps.presentation.R
import com.ps.presentation.ui.component.CustomText

@Composable
fun DetailRow(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .background(colorResource(id = R.color.purple_100))
    ) {
        Column(modifier = Modifier
            .weight(1F)
            .padding(10.dp)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
        Column(modifier = Modifier
            .weight(1F)
            .padding(10.dp)) {
            CustomText(
                modifier = Modifier.fillMaxWidth(),
                text = value,
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify
            )
        }
    }
}