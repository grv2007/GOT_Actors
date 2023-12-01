package com.ps.presentation.actorlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ps.common.utils.MainState
import com.ps.domain.model.ActorsModel
import com.ps.presentation.R
import com.ps.presentation.actorlist.component.ActorList
import com.ps.presentation.actorlist.component.IdleScreen
import com.ps.presentation.intent.MainIntent
import com.ps.presentation.ui.component.LoadingScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

const val ID = "id"
@AndroidEntryPoint
class ActorListFragment: Fragment() {

    private val viewModel: ActorListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                viewModel.userIntent.emit(MainIntent.FetchActors)
            }
        }

        val onItemClick: (Int) -> Unit = { id ->
            findNavController().navigate(
                R.id.action_actorListFragment_to_actorDetailFragment,
                bundleOf(ID to id)
            )
        }
        return ComposeView(requireContext()).apply {
            // Dispose of the Composition when the view's LifecycleOwner
            // is destroyed
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    // In Compose world
                    MainScreen(
                        vm = viewModel,
                        onButtonClick = onButtonClick,
                        onItemClick = onItemClick
                    )
                }
            }
        }
    }

    @Composable
    fun MainScreen(
        vm: ActorListViewModel,
        onButtonClick: () -> Unit,
        onItemClick: (Int) -> Unit
    ) {
        when (val state = vm.state.value) {
            is MainState.Idle -> IdleScreen(onButtonClick = onButtonClick)
            is MainState.Loading -> LoadingScreen()
            is MainState.Success -> ActorList(
                actors = (state.output as ActorsModel).list,
                onItemClick = onItemClick
            )
            is MainState.Error -> {
                IdleScreen(onButtonClick = onButtonClick)
                Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_SHORT).show()
            }
        }
    }


}