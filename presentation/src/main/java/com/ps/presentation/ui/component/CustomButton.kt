package com.ps.presentation.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun CustomButton(text: String, onButtonClick: () -> Unit) {
    Button(onClick = onButtonClick) {
        Text(text = text)
    }
}
