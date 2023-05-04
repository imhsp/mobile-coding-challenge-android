package com.imhsp.podcastandroid.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.imhsp.podcastandroid.R
import com.imhsp.podcastandroid.data.model.PodcastDetail
import com.imhsp.podcastandroid.ui.compose.util.ErrorPage
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
    val favouriteState by viewModel.favourite.observeAsState(false)
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        color = MaterialTheme.colors.background
    ) {
        Column {
            Row(
                modifier = Modifier.clickable { onBack() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Icon")
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = stringResource(id = R.string.back),
                    style = Typography.h5,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            when (uiState) {
                UIDataState.Loading -> {
                    Loader()
                }

                is UIDataState.Success -> {
                    val podDetails = (uiState as UIDataState.Success).podcastDetail
                    ItemDetail(podDetails, favouriteState) {
                        if (favouriteState) {
                            viewModel.removeFavourite(id)
                        } else {
                            viewModel.setFavourite(id)
                        }
                    }
                }

                is UIDataState.Failure -> {
                    ErrorPage {
                        viewModel.getPodDetails(id)
                    }
                }
            }
        }
    }
}

@Composable
private fun ItemDetail(
    uiState: PodcastDetail,
    favouriteState: Boolean,
    onFavouriteClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.title,
            style = Typography.h6,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.size(5.dp))
        Text(
            text = uiState.publisher,
            style = Typography.subtitle1,
            color = Color.Gray,
            fontStyle = FontStyle.Italic
        )

        Spacer(modifier = Modifier.size(15.dp))
        Image(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(15.dp)),
            painter = rememberAsyncImagePainter(uiState.image),
            contentDescription = "This is image of podcast"
        )

        Spacer(modifier = Modifier.size(10.dp))
        Button(
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.Magenta,
                contentColor = Color.White
            ),
            onClick = onFavouriteClick
        ) {
            Text(
                text = stringResource(id = if (favouriteState) R.string.favourited else R.string.favourite),
                style = Typography.h6
            )
        }

        Spacer(modifier = Modifier.size(10.dp))
        Text(text = uiState.description, style = Typography.body1)
    }
}