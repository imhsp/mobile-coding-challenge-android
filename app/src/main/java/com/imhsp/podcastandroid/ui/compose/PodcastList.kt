package com.imhsp.podcastandroid.ui.compose

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.imhsp.podcastandroid.R
import com.imhsp.podcastandroid.data.model.Podcast
import com.imhsp.podcastandroid.data.model.Results
import com.imhsp.podcastandroid.ui.compose.util.Loader
import com.imhsp.podcastandroid.ui.theme.Typography
import com.imhsp.podcastandroid.ui.viewmodel.PodcastListViewModel

@Composable
fun PodcastList(
    viewModel: PodcastListViewModel = hiltViewModel(),
    onNavigate: (id: String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Text(text = "Podcasts", style = Typography.h5, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(10.dp))

            val pagingItems: LazyPagingItems<Results> =
                viewModel.podcastList.collectAsLazyPagingItems()
            when(pagingItems.loadState.append){
                is LoadState.Loading -> {
                    Loader()
                }

                is LoadState.NotLoading -> {
                    LazyColumn {
                        items(
                            count = pagingItems.itemCount,
                            key = { index ->
                                val pod = pagingItems[index]
                                pod?.id ?: ""
                            }
                        ) { index ->
                            val pod = pagingItems[index] ?: return@items
                            ListItem(pod.podcast, onNavigate)
                        }
                    }
                }

                is LoadState.Error -> {

                }
            }
        }
    }
}

@Composable
private fun ListItem(pod: Podcast, onNavigate: (id: String) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(color = Color.Blue)
            .clickable { onNavigate(pod.id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "This is image of podcast"
        )
        Column {
            Text(text = pod.titleOriginal, style = Typography.body1)
            Text(text = pod.publisherOriginal, style = Typography.subtitle1)
            Text(text = "Favourited", style = Typography.caption)
        }

    }
}