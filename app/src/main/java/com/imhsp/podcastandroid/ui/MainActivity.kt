package com.imhsp.podcastandroid.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.imhsp.podcastandroid.ui.compose.PodcastApp
import com.imhsp.podcastandroid.ui.theme.PodcastAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PodcastAndroidTheme {
                val navController = rememberNavController()
                PodcastApp(navController)
            }
        }
    }
}