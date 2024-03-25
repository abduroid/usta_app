package com.example.ustaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.ustaapp.core.data.util.NetworkMonitor
import com.example.ustaapp.core.designsystem.theme.UstaTheme
import com.example.ustaapp.ui.UstaApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UstaTheme {
                UstaApp(networkMonitor = networkMonitor)
            }
        }
    }
}
