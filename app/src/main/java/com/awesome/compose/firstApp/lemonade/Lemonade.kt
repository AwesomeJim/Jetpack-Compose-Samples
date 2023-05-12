package com.awesome.compose.firstApp.lemonade

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import com.awesome.compose.firstApp.MyApp

class Lemonade: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {

}