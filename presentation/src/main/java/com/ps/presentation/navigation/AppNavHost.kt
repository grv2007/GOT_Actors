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
import com.ps.presentation.features.actorlist.actordetail.ui.ActorDetailMainScreen
import com.ps.presentation.features.actorlist.ui.ActorListMainScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    toolBarTitle: MutableState<String>,
    secondaryScreenHeader: MutableState<Boolean>,
) {
    NavHost(navController = navHostController, startDestination = Route.ACTOR_LIST_SCREEN.name) {
        composable(route = Route.ACTOR_LIST_SCREEN.name) {
            toolBarTitle.value = stringResource(id = R.string.title_actor_list)
            secondaryScreenHeader.value = false
            val onItemClick: (Int) -> Unit = { id ->
                navHostController.navigate("${Route.ACTOR_LIST_SCREEN.name}/$id")
            }
            ActorListMainScreen(onItemClick = onItemClick)

        }
        composable(
            route = "${Route.ACTOR_LIST_SCREEN.name}/{${SELECTED_ACTOR_ID}}",
            arguments = listOf(
                navArgument(SELECTED_ACTOR_ID) {
                    type = NavType.IntType
                }
            )
        ) {

            toolBarTitle.value = stringResource(id = R.string.title_actor_details)
            secondaryScreenHeader.value = true

            it.arguments?.getInt(SELECTED_ACTOR_ID)?.let { id ->
                ActorDetailMainScreen(id)
            }
        }
    }
}
