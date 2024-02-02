package com.elbaz.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.elbaz.sample.ui.screens.main.MainScreenPreview
import com.elbaz.sample.ui.theme.JobFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()


        setContent {
            JobFinderTheme {
                // A surface container using the 'background' color from the theme
                MainScreenPreview()
            }
        }


    }
}
