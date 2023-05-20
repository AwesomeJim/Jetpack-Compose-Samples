package com.example.affirmations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.affirmations.data.Datasource
import com.example.affirmations.model.Affirmation
import com.example.affirmations.model.Topic
import com.example.affirmations.ui.theme.AffirmationsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AffirmationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AffirmationsApp()
                }
            }
        }
    }
}

@Composable
fun AffirmationsApp() {
    AffirmationsTheme {
        // AffirmationList(affirmationList = Datasource().loadAffirmations())
        TopicsList(topicsList = Datasource().topics)
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), modifier = Modifier.padding(8.dp)
    ) {
        Column {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.headlineSmall
            )

        }


    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn {
        items(affirmationList) { affirmation ->
            AffirmationCard(affirmation)
        }
    }

}


//@Preview(name = "Affirmations", showSystemUi = false)
@Composable
private fun AffirmationCardPreview() {
    AffirmationCard(Affirmation(R.string.affirmation1, R.drawable.image1))
}


@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ), modifier = Modifier
            .height(68.dp)
    ) {
        Row {
            Image(
                painter = painterResource(topic.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(width = 68.dp, height = 68.dp)
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = LocalContext.current.getString(topic.stringResourceId),
                    modifier = Modifier.padding(bottom = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        contentScale = ContentScale.Fit,
                        colorFilter = ColorFilter.tint(Color.Black)
                    )
                    Text(
                        text = topic.number.toString(),
                        modifier = Modifier.padding(start = 8.dp),
                        style = MaterialTheme.typography.labelMedium
                    )
                }
            }
        }


    }
}

@Composable
fun TopicsList(topicsList: List<Topic>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(topicsList) { topic ->
            TopicCard(topic)
        }
    }

}


@Preview(name = "TopicItem")
@Composable
private fun TopicCardPreview() {
    TopicCard(Topic(R.string.photography, 23, R.drawable.photography))
}


@Preview(name = "TopicItem")
@Composable
private fun TopicsListPreview() {
    TopicsList(topicsList = Datasource().topics)
}
