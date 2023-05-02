package com.imhsp.podcastandroid.ui.compose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PodcastApp(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "list") {
        composable("list") {
            PodcastList {
                navController.navigate("detail")
            }
        }
        composable("detail") {
            PodcastDetail {
                navController.popBackStack()
            }
        }
    }
}