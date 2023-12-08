package com.ps.presentation.features.actordetail.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.R
import com.ps.presentation.ui.component.CustomCardImage
import com.ps.presentation.ui.component.CustomText

@Composable
fun Details(detail: ActorDetailModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val url = detail.imageUrl
        val painter = rememberAsyncImagePainter(model = url)
        CustomCardImage(
            painter = painter,
            contentDescription = detail.fullName,
            size = 300
        )
        CustomText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp),
            text = detail.fullName,
            color = colorResource(id = R.color.white),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
        DetailRow(name = stringResource(R.string.first_name), value = detail.firstName)
        DetailRow(name = stringResource(R.string.last_name), value = detail.lastName)
        DetailRow(name = stringResource(R.string.title), value = detail.title)
        DetailRow(name = stringResource(R.string.family), value = detail.family)
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
    Details(actorDetailModel)
}
