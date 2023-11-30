package com.ps.presentation.actordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.ps.domain.model.ActorDetailModel
import com.ps.gotactors.view.ActorDetailViewModel
import com.ps.presentation.R
import com.ps.presentation.actorlist.ActorListFragment
import com.ps.presentation.view.MainIntent
import com.ps.presentation.view.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActorDetailFragment : Fragment() {
    private val viewModel: ActorDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getInt(ActorListFragment.ID)?.let { id ->
            lifecycleScope.launch {
                viewModel.userIntent.send(MainIntent.FetchActorDetail(id))
            }
        }
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    // In Compose world
                    MainScreen(viewModel)
                }
            }
        }
    }

    @Composable
    fun MainScreen(vm: ActorDetailViewModel) {
        when (val state = vm.state.value) {
            is MainState.Loading -> LoadingScreen()
            is MainState.Success -> DetailScreen(detail = (state.output as ActorDetailModel))
            else -> ErrorScreen()
        }
    }

    @Composable
    fun DetailScreen(detail: ActorDetailModel) {
        Column(
            modifier = Modifier.fillMaxWidth()
                .background(
                    colorResource(id = R.color.purple_100)
                )
                //.verticalScroll(rememberScrollState())
        ) {
            val url = detail.imageUrl
            val painter = rememberImagePainter(data = url)
            Image(
                painter = painter,
                contentDescription = "Image ${detail.fullName}",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                text = detail.fullName,
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
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp)) {
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
    @Composable
    fun LoadingScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.purple_100)
                ), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ErrorScreen() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    colorResource(id = R.color.purple_100)
                ), contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Something went wrong!",
                color = colorResource(id = R.color.white)
            )
        }
    }
}