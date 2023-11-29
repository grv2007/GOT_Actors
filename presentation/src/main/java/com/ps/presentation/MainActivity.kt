package com.ps.presentation


import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.ps.domain.model.Actor
import com.ps.domain.model.ActorsModel
import com.ps.gotactors.view.MainViewModel
import com.ps.presentation.ui.theme.GOTActorsTheme
import com.ps.presentation.view.MainIntent
import com.ps.presentation.view.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : FragmentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchActors)
            }
        }
        setContent {
            GOTActorsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(vm = mainViewModel, onButtonClick = onButtonClick)
                }
            }
        }
    }

    @Composable
    fun MainScreen(vm: MainViewModel, onButtonClick: () -> Unit) {
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
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = onButtonClick) {
                Text(text = "Fetch Actors")
            }
        }
    }

    @Composable
    fun LoadingScreen() {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                .fillMaxWidth()
                .height(100.dp),
        ) {
            val url = actor.imageUrl
            val painter = rememberImagePainter(data = url)
            Image(
                painter = painter,
                contentDescription = "Image ${actor.fullName}",
                modifier = Modifier.size(100.dp),
                contentScale = ContentScale.FillHeight
            )
            Column (modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp) ) {
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = actor.fullName,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 20.sp
                )
                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = actor.family,
                    color = Color.Gray
                )
            }
        }
    }
}