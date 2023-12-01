package com.ps.presentation.actorlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.compose.rememberImagePainter
import com.ps.common.utils.MainState
import com.ps.domain.model.Actor
import com.ps.domain.model.ActorsModel
import com.ps.gotactors.view.ActorListViewModel
import com.ps.presentation.R
import com.ps.presentation.intent.MainIntent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActorListFragment: Fragment() {
    companion object {
        val ID = "id"
    }
    private val viewModel: ActorListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                viewModel.userIntent.send(MainIntent.FetchActors)
            }
        }

        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    // In Compose world
                    MainScreen(vm = viewModel, onButtonClick = onButtonClick)
                }
            }
        }
    }

    @Composable
    fun MainScreen(vm: ActorListViewModel, onButtonClick: () -> Unit) {
        when (val state = vm.state.value) {
            is MainState.Idle -> IdleScreen(onButtonClick = onButtonClick)
            is MainState.Loading -> LoadingScreen()
            is MainState.Success -> ActorList(actors = (state.output as ActorsModel).list)
            is MainState.Error -> {
                IdleScreen(onButtonClick = onButtonClick)
                Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

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

    @Composable
    fun LoadingScreen() {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    @Composable
    fun ActorList(actors: List<Actor>) {
        LazyColumn {
            items(items = actors) {
                ActorItem(actor = it)
                Divider(color = Color.LightGray, modifier = Modifier.padding(vertical = 4.dp))
            }
        }
    }

    @Composable
    fun ActorItem(actor: Actor) {
        Row(
            modifier = Modifier
                .fillMaxWidth().background(
                    colorResource(id = R.color.purple_100)
                )
                .height(100.dp).clickable {
                    findNavController().navigate(
                        R.id.action_actorListFragment_to_actorDetailFragment,
                        bundleOf(ID to actor.id)
                    )
                },
        ) {
            val url = actor.imageUrl
            val painter = rememberImagePainter(data = url)
            Image(
                painter = painter,
                contentDescription = "Image ${actor.fullName}",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillBounds
            )
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp) ) {
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