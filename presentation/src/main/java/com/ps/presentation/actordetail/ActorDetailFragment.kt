package com.ps.presentation.actordetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ps.common.utils.MainState
import com.ps.domain.model.ActorDetailModel
import com.ps.presentation.actordetail.component.DetailScreen
import com.ps.presentation.actorlist.ID
import com.ps.presentation.intent.MainIntent
import com.ps.presentation.ui.component.ErrorScreen
import com.ps.presentation.ui.component.LoadingScreen
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
        arguments?.getInt(ID)?.let { id ->
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
            is MainState.Success -> DetailScreen(detail = state.output as ActorDetailModel)
            else -> ErrorScreen()
        }
    }
}