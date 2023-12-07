package com.ps.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ps.presentation.R
import com.ps.presentation.features.actorlist.actordetail.ActorDetailViewModel
import com.ps.presentation.features.actorlist.ActorListViewModel
import com.ps.presentation.navigation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(actorListViewModel: ActorListViewModel, actorDetailViewModel: ActorDetailViewModel) {
    val toolbarTitle = remember { mutableStateOf("") }
    val secondaryScreenHeader = remember { mutableStateOf(false) }
    val navHostController: NavHostController = rememberNavController()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = toolbarTitle.value,
                        maxLines = 1
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = Color.White,
                ),
                navigationIcon = {
                    if (secondaryScreenHeader.value) {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }
                    }
                }

            )
        }, content = {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .paint(
                        painterResource(id = R.drawable.splash_bg),
                        contentScale = ContentScale.FillBounds
                    )
            ) {
                AppNavHost(
                    navHostController = navHostController,
                    actorListViewModel = actorListViewModel,
                    actorDetailViewModel = actorDetailViewModel,
                    toolBarTitle = toolbarTitle,
                    secondaryScreenHeader = secondaryScreenHeader,
                )
            }
        }
        )
    }

}