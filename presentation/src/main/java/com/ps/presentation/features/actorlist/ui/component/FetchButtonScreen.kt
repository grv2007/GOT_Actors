package com.ps.presentation.features.actorlist.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ps.presentation.R
import com.ps.presentation.ui.component.ButtonScreen

@Composable
fun FetchButtonScreen(onButtonClick: () -> Unit) {
    ButtonScreen(
        text = stringResource(id = R.string.button_fetch_actors),
        onButtonClick = onButtonClick
    )
}