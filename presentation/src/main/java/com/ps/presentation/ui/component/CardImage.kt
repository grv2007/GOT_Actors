package com.ps.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun CardImage(
    painter: Painter,
    contentDescription: String?,
    size: Int
) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        )
    ) {
        Image(
            painter = painter,
            contentDescription = "Image $contentDescription",
            modifier = Modifier.size(size.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}