package com.ps.presentation.actorlist.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.ps.presentation.R

@Composable
fun IdleScreen(onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize().paint(
        // Replace with your image id
        painterResource(id = R.drawable.splash_bg),
        contentScale = ContentScale.FillBounds),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Actors")
        }
    }
}
