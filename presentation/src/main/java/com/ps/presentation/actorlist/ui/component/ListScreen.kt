package com.ps.presentation.actorlist.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ps.domain.model.Actor
import com.ps.presentation.R


@Composable
fun ActorList(actors: List<Actor>, onItemClick: (Int) -> Unit) {
    LazyColumn {
        items(items = actors) {
            ActorItem(actor = it, onItemClick = onItemClick)
            Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 4.dp))
        }
    }
}

@Composable
fun ActorItem(actor: Actor, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    colorResource(id = R.color.purple_100)
                )
                .height(100.dp)
                .clickable { onItemClick.invoke(actor.id) }
        ) {
            val url = actor.imageUrl
            val painter = rememberAsyncImagePainter(model = url)
            Image(
                painter = painter,
                contentDescription = "Image ${actor.fullName}",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = actor.fullName,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.black),
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = actor.family,
                    color = colorResource(id = R.color.white)
                )
            }
        }
    }
}