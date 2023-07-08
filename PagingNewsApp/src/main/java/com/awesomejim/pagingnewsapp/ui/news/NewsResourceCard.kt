package com.awesomejim.pagingnewsapp.ui.news

/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.ConfigurationCompat
import coil.compose.AsyncImage
import com.awesomejim.pagingnewsapp.R
import com.awesomejim.pagingnewsapp.model.Article
import com.awesomejim.pagingnewsapp.model.previewNewsResources
import com.awesomejim.pagingnewsapp.ui.theme.MyNewsAppTheme
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


/**
 * [NewsResource] card used on the following screens: For You, Episodes, Saved
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsResourceCardExpanded(
    newsResource: Article,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val clickActionLabel = "Open Resource Link"
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        // Use custom label for accessibility services to communicate button's action to user.
        // Pass null for action to only override the label and not the actual action.
        modifier = modifier.semantics {
            onClick(label = clickActionLabel, action = null)
        }.padding(top = 6.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column {
            if (!newsResource.urlToImage.isNullOrEmpty()) {
                Row {
                    NewsResourceHeaderImage(newsResource.urlToImage)
                }
            }
            Box(
                modifier = Modifier.padding(16.dp)
            ) {
                Column {
                    NewsResourceTitle(
                        newsResource.title,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        NewsResourceAuthors(listOf(newsResource.author))
                        Spacer(modifier = Modifier.weight(1f))
                        NewsResourceDate(newsResource.publishedAt)
                    }
                    //  NewsResourceShortDescription(newsResource.description)
                }
            }
        }
    }
}

@Composable
fun NewsResourceHeaderImage(
    headerImageUrl: String
) {
    AsyncImage(
        placeholder = if (LocalInspectionMode.current) {
            painterResource(R.drawable.ic_placeholder_default)
        } else {
            // TODO b/228077205, show specific loading image visual
            null
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        contentScale = ContentScale.Crop,
        model = headerImageUrl,
        // TODO b/226661685: Investigate using alt text of  image to populate content description
        contentDescription = null // decorative image
    )
}

@Composable
fun NewsResourceAuthors(
    authors: List<String>
) {
    if (authors.isNotEmpty()) {
        // Only display first author for now
        val author = authors[0]

        val locale = ConfigurationCompat.getLocales(LocalConfiguration.current).get(0)

        val authorNameFormatted = if (locale != null) {
            author.uppercase(locale)
        } else {
            author.uppercase()
        }

        //  val authorImageUrl = author.imageUrl

        val authorImageModifier = Modifier
            .clip(CircleShape)
            .size(24.dp)

        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = authorImageModifier
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(4.dp),
                imageVector = Icons.Rounded.Person,
                contentDescription = null // decorative image
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(authorNameFormatted, style = MaterialTheme.typography.labelSmall)
        }
    }
}

@Composable
fun NewsResourceTitle(
    newsResourceTitle: String,
    modifier: Modifier = Modifier
) {
    Text(newsResourceTitle, style = MaterialTheme.typography.headlineSmall, modifier = modifier)
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun dateFormatted(publishDate: String): String {
    var zoneId by remember { mutableStateOf(ZoneId.systemDefault()) }

    val context = LocalContext.current

    DisposableEffect(context) {
        val receiver = TimeZoneBroadcastReceiver(
            onTimeZoneChanged = { zoneId = ZoneId.systemDefault() }
        )
        receiver.register(context)
        onDispose {
            receiver.unregister(context)
        }
    }

//    return DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss")
//        .withZone(zoneId).format(publishDate)
    val dateTime = LocalDateTime.parse(publishDate, DateTimeFormatter.ISO_OFFSET_DATE_TIME)
    val localDateTime = dateTime.atZone(zoneId)
    val formatter = DateTimeFormatter.ofPattern("MMM d, yyyy HH:mm:ss")
    return localDateTime.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NewsResourceDate(
    publishDate: String
) {
    Text(
        dateFormatted(publishDate),
        style = MaterialTheme.typography.labelSmall
    )

}

@Composable
fun NewsResourceLink(
    newsResource: Article
) {
    TODO()
}

@Composable
fun NewsResourceShortDescription(
    newsResourceShortDescription: String
) {
    Text(newsResourceShortDescription, style = MaterialTheme.typography.bodyLarge)
}

@Composable
fun NewsResourceTopics(
    newsResource: Article
) {
    TODO()
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview("NewsResourceCardExpanded")
@Composable
fun ExpandedNewsResourcePreview() {
    MyNewsAppTheme {
        Surface {
            NewsResourceCardExpanded(
                newsResource = previewNewsResources[0],
                onClick = {}
            )
        }
    }
}
