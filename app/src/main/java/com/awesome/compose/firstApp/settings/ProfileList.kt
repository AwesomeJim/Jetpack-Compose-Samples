package com.awesome.compose.firstApp.settings


/**
 * Created by Awesome Jim on.
 * 02/10/2023
 */


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Preview(showSystemUi = true)
@Composable
fun ListView() {
    val items = listOf(
        mapOf(
            "title" to "Jenny",
            "subtitle" to "jenny@example.com",
            "photoUrl" to "https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80"
        ),
        mapOf(
            "title" to "James",
            "subtitle" to "james@example.com",
            "photoUrl" to "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80"
        ),
        mapOf(
            "title" to "Cassidy",
            "subtitle" to "cassidy@example.com",
            "photoUrl" to "https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=256&q=80"
        ),
        mapOf(
            "title" to "Kim",
            "subtitle" to "kim@example.com",
            "photoUrl" to "https://images.unsplash.com/photo-1494790108377-be9c29b29330?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80"
        ),
        mapOf(
            "title" to "Samantha",
            "subtitle" to "samantha@example.com",
            "photoUrl" to "https://images.unsplash.com/photo-1580489944761-15a19d654956?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=256&q=80"
        ),
        mapOf(
            "title" to "John",
            "subtitle" to "john@example.com",
            "photoUrl" to "https://images.unsplash.com/photo-1580518380430-2f84c0a7fb85?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=256&q=80"
        ),
    )

    LazyColumn {
        items(items) { item ->
            val photo = item["photoUrl"] as String
            val title = item["title"] as String
            val subtitle = item["subtitle"] as String

            Surface(onClick = { /* TODO */ }, shape = MaterialTheme.shapes.large) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth().padding(16.dp)
                ) {
                    AsyncImage(
                        model = photo,
                        modifier = Modifier.size(58.dp).clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )
                    Column {
                        Text(text = title, maxLines = 1, overflow = TextOverflow.Ellipsis)
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}