package com.aagamshah.slipstreampicks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.aagamshah.slipstreampicks.navigation.NavGraph
import com.aagamshah.slipstreampicks.ui.theme.SlipstreamPicksTheme
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MobileAds.initialize(this)
        enableEdgeToEdge()
        setContent {
            SlipstreamPicksTheme {
                Surface {
                    val navController = rememberNavController()
                    NavGraph(navController = navController)
                }
            }
        }
    }
}