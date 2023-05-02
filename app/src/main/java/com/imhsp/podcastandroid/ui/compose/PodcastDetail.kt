package com.imhsp.podcastandroid.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.imhsp.podcastandroid.R
import com.imhsp.podcastandroid.ui.theme.Typography

@Composable
fun PodcastDetail(onBack: () -> Unit) {
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
            ItemDetail()
        }
    }
}

@Composable
private fun ItemDetail() {
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
            Text(text = "Podcast title", style = Typography.body1)
            Text(text = "host", style = Typography.subtitle1)
            Text(text = "Favourited", style = Typography.caption)
        }

    }
}