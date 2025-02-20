package com.aagamshah.slipstreampicks

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.aagamshah.slipstreampicks.presentation.mainscreen.MainScreen
import com.aagamshah.slipstreampicks.ui.theme.SlipstreamPicksTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SlipstreamPicksTheme {
                Surface {
                    val navController = rememberNavController()
                    MainScreen(navController = navController)
                }
            }
        }
    }
}