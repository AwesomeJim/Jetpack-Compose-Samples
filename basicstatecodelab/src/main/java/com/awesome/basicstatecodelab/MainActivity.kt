package com.awesome.basicstatecodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.awesome.basicstatecodelab.ui.theme.MyComposeApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyComposeApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    WellnessScreen()
                }
            }
        }
    }
}

@Composable
fun WellnessScreenPreview() {
    WellnessScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyComposeApplicationTheme {
        WellnessScreenPreview()
    }
}