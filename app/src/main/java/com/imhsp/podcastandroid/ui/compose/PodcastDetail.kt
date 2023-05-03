package com.imhsp.podcastandroid.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.imhsp.podcastandroid.R
import com.imhsp.podcastandroid.data.model.PodcastDetail
import com.imhsp.podcastandroid.ui.compose.util.Loader
import com.imhsp.podcastandroid.ui.theme.Typography
import com.imhsp.podcastandroid.ui.viewmodel.PodcastDetailViewModel
import com.imhsp.podcastandroid.ui.viewmodel.UIDataState

@Composable
fun PodcastDetail(
    id: String?,
    viewModel: PodcastDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.getPodDetails(id)
    }

    val uiState by viewModel.podDetails.observeAsState(UIDataState.Loading)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Text(
                modifier = Modifier.clickable { onBack() },
                text = "< Back",
                style = Typography.h5,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(10.dp))
            when (uiState) {
                UIDataState.Loading -> {
                    Loader()
                }

                is UIDataState.Success -> {
                    ItemDetail((uiState as UIDataState.Success).podcastDetail)
                }

                is UIDataState.Failure -> {

                }
            }
        }
    }
}

@Composable
private fun ItemDetail(uiState: PodcastDetail) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(color = Color.Blue),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "This is image of podcast"
        )
        Column {
            Text(text = uiState.title, style = Typography.body1)
            Text(text = uiState.publisher, style = Typography.subtitle1)
            Text(text = uiState.description, style = Typography.caption)
        }

    }
}