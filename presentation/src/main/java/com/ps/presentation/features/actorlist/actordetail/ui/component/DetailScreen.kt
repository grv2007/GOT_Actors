package com.ps.presentation.features.actorlist.actordetail.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.R
import com.ps.presentation.ui.component.CardImage

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
        CardImage(
            painter = painter,
            contentDescription = detail.fullName,
            size = 300
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
            .padding(horizontal = 50.dp, vertical = 10.dp)
            .background(colorResource(id = R.color.purple_100))
    ) {
        Column(modifier = Modifier.weight(1F).padding(10.dp)) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = name,
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            )
        }
        Column(modifier = Modifier.weight(1F).padding(10.dp)) {
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
@Preview
@Composable
fun DetailScreenPreview() {
     val id = 1
     val firstName = "Gaurav"
     val lastName = "Dhingra"
     val fullName = "Gaurav Dhingra"
     val title = "Rock"
     val family = "Dhingra"
     val imageUrl = "url/image.jpg"

    val actorDetailModel = ActorDetailModel(
        id = id,
        firstName = firstName,
        lastName = lastName,
        fullName = fullName,
        title = title,
        family = family,
        imageUrl = imageUrl
    )
    DetailScreen(actorDetailModel)
}
