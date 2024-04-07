package com.elbaz.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.elbaz.sample.ui.screens.main.MainScreen
import com.elbaz.sample.ui.theme.SampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()


        setContent {
            SampleTheme {
                // A surface container using the 'background' color from the theme
                MainScreen()
            }
        }


    }
}
