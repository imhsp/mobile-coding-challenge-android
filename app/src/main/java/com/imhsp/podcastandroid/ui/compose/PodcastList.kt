package com.imhsp.podcastandroid.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.imhsp.podcastandroid.data.model.Podcast
import com.imhsp.podcastandroid.data.model.Results
import com.imhsp.podcastandroid.ui.compose.util.ErrorPage
import com.imhsp.podcastandroid.ui.compose.util.Loader
import com.imhsp.podcastandroid.ui.theme.Typography
import com.imhsp.podcastandroid.ui.viewmodel.PodcastListViewModel
import com.imhsp.podcastandroid.R

@Composable
fun PodcastList(
    viewModel: PodcastListViewModel = hiltViewModel(),
    onNavigate: (id: String) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Podcasts", style = Typography.h4, fontWeight = FontWeight.Bold)

            val pagingItems: LazyPagingItems<Results> =
                viewModel.podcastList.collectAsLazyPagingItems()
            when (pagingItems.loadState.append) {
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
                    ErrorPage {
                        pagingItems.loadState.refresh
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItem(pod: Podcast, onNavigate: (id: String) -> Unit) {
    Spacer(modifier = Modifier.height(5.dp))
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onNavigate(pod.id) }
    ) {
        Image(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(15.dp)),
            painter = rememberAsyncImagePainter(pod.thumbnail),
            contentDescription = "This is image of podcast"
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = pod.titleOriginal, style = Typography.h6, fontWeight = FontWeight.Bold)
            Text(
                text = pod.publisherOriginal,
                style = Typography.subtitle1,
                color = Color.Gray,
                fontStyle = FontStyle.Italic
            )
            if (pod.isFavourite) {
                Text(
                    text = stringResource(id = R.string.favourited),
                    style = Typography.subtitle1,
                    color = Color.Red
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(5.dp))
    Divider(color = Color.LightGray, thickness = 1.dp)
}