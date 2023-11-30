package com.ps.presentation.actordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.colorResource
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ps.domain.model.ActorDetailModel
import com.ps.gotactors.view.ActorDetailViewModel
import com.ps.presentation.R
import com.ps.presentation.view.MainIntent
import com.ps.presentation.view.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ActorDetailFragment: Fragment() {
    private val viewModel: ActorDetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        lifecycleScope.launch {
            viewModel.userIntent.send(MainIntent.FetchActorDetail(1))
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

    }
    @Composable
    fun LoadingScreen() {
        Box(modifier = Modifier.fillMaxSize().background(
            colorResource(id = R.color.purple_100)
        ), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    @Composable
    fun ErrorScreen() {
        Box(modifier = Modifier.fillMaxSize().background(
            colorResource(id = R.color.purple_100)
        ), contentAlignment = Alignment.Center) {
            Text(
                text = "Something went wrong!",
                color = colorResource(id = R.color.white)
            )
        }
    }
}