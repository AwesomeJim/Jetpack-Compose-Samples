package com.awesome.compose.firstApp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.awesome.compose.firstApp.data.SampleData.columnComposable
import com.awesome.compose.firstApp.data.SampleData.imageComposable
import com.awesome.compose.firstApp.data.SampleData.rowComposable
import com.awesome.compose.firstApp.data.SampleData.textComposable
import com.awesome.compose.firstApp.ui.theme.MyComposeApplicationTheme

class BirthdayCard : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BirthdayGreetingWithImage(
                        stringResource(R.string.happy_birthday_message),
                        stringResource(R.string.signature_text)
                    )
                }
            }
        }
    }
}


@Composable
fun BirthdayGreetingWithText(name: String, from: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = name,
            fontSize = 36.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(20.dp)
        )
        Text(
            text = from,
            fontSize = 24.sp,
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(alignment = Alignment.End)
        )
    }
}

@Composable
fun BirthdayGreetingWithImage(message: String, from: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.androidparty)
    //Step 3 create a box to overlap image and texts
    Box {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop

        )
        BirthdayGreetingWithText(name = message, from = from, modifier = modifier)
    }


}


@Composable
fun TextShadow() {
    val offset = Offset(5.0f, 10.0f)
    Text(
        text = "Hello world!",
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue, offset = offset, blurRadius = 3f
            )
        )
    )
}


@Preview(
    name = "My Preview",
    showBackground = false,
    showSystemUi = false
)
@Composable
fun GreetingPreview() {
    MyComposeApplicationTheme {
        BirthdayGreetingWithImage(
            stringResource(R.string.happy_birthday_message),
            stringResource(R.string.signature_text)
        )
    }
}

@Composable
fun ComposeArticle(
    title: String,
    summary: String,
    description: String,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.bg_compose_background)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = summary,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(16.dp)
        )
        Text(
            text = description,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(26.dp)
        )

    }

}


@Preview(
    name = "ComposeArticle Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun ComposeArticlePreview() {
    MyComposeApplicationTheme {
        ComposeArticle(
            stringResource(R.string.compose_title),
            stringResource(R.string.compose_summary),
            stringResource(R.string.compose_description)
        )
    }
}


@Composable
fun TaskManager(
    title: String,
    summary: String,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.ic_task_completed)
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = modifier.padding(top = 24.dp, bottom = 8.dp)
        )
        Text(
            text = summary,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(6.dp)
        )

    }

}


@Preview(
    name = "ComposeArticle Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun TaskManagerPreview() {
    MyComposeApplicationTheme {
        TaskManager(
            stringResource(R.string.task_manager_title),
            stringResource(R.string.task_manager_complement),
        )
    }
}

@Composable
fun ComposableInfoCard(
    title: String,
    summary: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(bottom = 16.dp)
        )
        Text(
            text = summary,
            textAlign = TextAlign.Justify,
            modifier = modifier.padding(6.dp)
        )

    }
}

@Preview(
    name = "ComposeArticle Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun QuadrantItemPreview() {
    MyComposeApplicationTheme {
        val message = textComposable
        ComposableInfoCard(
            message.author,
            message.body,
            backgroundColor = Color.Green
        )
    }
}

@Composable
fun ComposeQuadrantApp(
) {
    Column(Modifier.fillMaxWidth()) {
        Row(Modifier.weight(1f)) {
            // Quadrant to be displayed on first half of the screen
            ComposableInfoCard(
                textComposable.author,
                textComposable.body,
                backgroundColor = Color.Green,
                modifier = Modifier.weight(1f)
            )
            // Quadrant to be displayed on second half of the screen
            ComposableInfoCard(
                imageComposable.author,
                imageComposable.body,
                backgroundColor = Color.Yellow,
                modifier = Modifier.weight(1f)
            )
        }
        Row(Modifier.weight(1f)) {
            // Quadrant to be displayed on first half of the screen
            ComposableInfoCard(
                rowComposable.author,
                rowComposable.body,
                backgroundColor = Color.Cyan,
                modifier = Modifier.weight(1f)
            )
            // Quadrant to be displayed on second half of the screen
            ComposableInfoCard(
                columnComposable.author,
                columnComposable.body,
                backgroundColor = Color.LightGray,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(
    name = "ComposeArticle Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun ComposeQuadrantAppPreview() {
    MyComposeApplicationTheme {
        ComposeQuadrantApp()
    }
}


@Composable
fun BusinessCardHeader(
    title: String,
    summary: String,
    modifier: Modifier = Modifier
) {
    val image = painterResource(R.drawable.android_logo)
    Column(
        modifier = modifier
            .background(Color(0xFF073042))
            .padding(bottom = 100.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = image,
            contentDescription = "Android Logo",
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
        )
        Text(
            text = title,
            fontSize = 24.sp,
            color = Color.White,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Left,
            modifier = modifier.padding(bottom = 8.dp)
        )
        Text(
            text = summary,
            fontSize = 16.sp,
            textAlign = TextAlign.Justify,
            color = Color.Green,
            modifier = modifier.padding(3.dp)
        )

    }

}

@Preview(
    name = "ComposeArticle Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun BusinessCardHeaderPreview() {
    MyComposeApplicationTheme {
        BusinessCardHeader("Awesome Jim", "Tech Lead")
    }
}


@Composable
fun BusinessFooterItem(
    icon: ImageVector,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(36.dp)
            .padding(bottom = 1.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Divider(color = Color.LightGray, thickness = 1.dp)
        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            // Add a horizontal space between the image and the column
            Spacer(modifier = Modifier.width(48.dp))
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = Color.Green
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                color = Color.White,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Start
            )
        }
    }

}

@Preview(
    name = "Business Footer Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun BusinessFooterItemPreview() {
    MyComposeApplicationTheme {
        BusinessFooterItem(Icons.Rounded.Menu, "Tech Lead", "Awesome Jim")
    }
}


@Composable
fun BusinessFooter(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxHeight(300F)
            .background(Color(0xFF073042)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BusinessCardHeader("Awesome Jim", "Tech Lead")
        BusinessFooterItem(Icons.Rounded.Phone, "phone number", "+25470000000")
        BusinessFooterItem(Icons.Rounded.Share, "Twitter handle", "@AwesomeJim")
        BusinessFooterItem(Icons.Rounded.Email, "Email Address", "awesomejim@gmail.com")
    }
}

@Preview(
    name = "Business Footer Preview",
    showBackground = true,
    showSystemUi = false
)
@Composable
fun BusinessFooterPreview() {
    MyComposeApplicationTheme {
        BusinessFooter()
    }
}
