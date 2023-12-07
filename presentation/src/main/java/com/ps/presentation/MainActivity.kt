package com.ps.presentation


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import com.ps.presentation.features.actorlist.actordetail.ActorDetailViewModel
import com.ps.presentation.features.actorlist.ActorListViewModel
import com.ps.presentation.ui.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val actorListViewModel: ActorListViewModel by viewModels()
    private val actorDetailViewModel: ActorDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen(
                    actorListViewModel,
                    actorDetailViewModel
                )
            }
        }
    }
}
