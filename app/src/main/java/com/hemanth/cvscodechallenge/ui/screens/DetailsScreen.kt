package com.hemanth.cvscodechallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.hemanth.cvscodechallenge.viewmodel.ImageViewModel

@Composable
fun DetailScreen(title: String?, viewModel: ImageViewModel) {
    val images by viewModel.images.observeAsState()
    val item = images?.items?.find { it?.title == title }

    if (item != null) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        )  {
            Column {
                AsyncImage(
                    model = item.media?.m,
                    contentDescription = null,
                    modifier = Modifier
                        .size(390.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(text = "Title: ${item.title}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(7.dp))
                Text(text = "Author: ${item.author}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(7.dp))
                Text(text = "Author ID: ${item.authorId}", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(7.dp))
                Text(text = "Date Taken: ${item.dateTaken}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(7.dp))
                Text(text = "Published: ${item.published}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(7.dp))
                Text(text = "Tags: ${item.tags}", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(7.dp))
                Text(text = "Description: ${item.description}", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(7.dp))
            }
        }
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}
