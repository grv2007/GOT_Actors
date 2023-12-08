package com.ps.presentation.features.actorlist.ui.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ps.domain.model.Actor


@Composable
fun ActorList(actors: List<Actor>, onItemClick: (Int) -> Unit) {
    LazyColumn {
        items(items = actors) {
            ActorItem(actor = it, onItemClick = onItemClick)
            Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}
