package com.ps.presentation.actordetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.R

@Composable
fun DetailScreen(detail: ActorDetailModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val url = detail.imageUrl
        val painter = rememberAsyncImagePainter(model = url)
        Image(
            painter = painter,
            contentDescription = "Image ${detail.fullName}",
            modifier = Modifier.size(300.dp),
            contentScale = ContentScale.FillBounds
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            text = detail.fullName,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        DetailRow(name = "First Name:", value = detail.firstName)
        DetailRow(name = "Last Name:", value = detail.lastName)
        DetailRow(name = "Title:", value = detail.title)
        DetailRow(name = "Family:", value = detail.family)
    }
}

@Composable
fun DetailRow(name: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)
    ) {
        Column(modifier = Modifier.weight(1F)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
        Column(modifier = Modifier.weight(1F)) {
            Text(
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
