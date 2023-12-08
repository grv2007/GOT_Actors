package com.ps.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ps.presentation.R
import com.ps.presentation.features.actordetail.ui.ActorDetailScreen
import com.ps.presentation.features.actorlist.ui.ActorListScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    toolBarTitle: MutableState<String>,
    secondaryScreenHeader: MutableState<Boolean>,
) {
    NavHost(navController = navHostController, startDestination = Route.ACTOR_LIST_SCREEN.getRoute()) {
        composable(route = Route.ACTOR_LIST_SCREEN.getRoute()) {
            toolBarTitle.value = stringResource(id = R.string.title_actor_list)
            secondaryScreenHeader.value = false
            val onItemClick: (Int) -> Unit = { id ->
                navHostController.navigate(Route.ACTOR_DETAIL_SCREEN.getRoute(id))
            }
            ActorListScreen(onItemClick = onItemClick)

        }
        composable(
            route = Route.ACTOR_DETAIL_SCREEN.getRoutePlaceHolder(),
            arguments = listOf(
                navArgument(SELECTED_ACTOR_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            toolBarTitle.value = stringResource(id = R.string.title_actor_details)
            secondaryScreenHeader.value = true

            it.arguments?.getInt(SELECTED_ACTOR_ID)?.let { id ->
                ActorDetailScreen(id)
            }
        }
    }
}

