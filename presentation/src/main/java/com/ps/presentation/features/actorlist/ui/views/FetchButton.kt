package com.ps.presentation.features.actorlist.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ps.presentation.R
import com.ps.presentation.ui.component.CustomButton

@Composable
fun FetchButton(onButtonClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CustomButton(
            text = stringResource(id = R.string.button_fetch_actors),
            onButtonClick = onButtonClick
        )
    }
}