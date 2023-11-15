package com.example.spb_bestplacenavigationadaptivelayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import com.example.spb_bestplacenavigationadaptivelayout.ui.theme.SPb_BestPlaceNavigationAdaptiveLayoutTheme
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SPb_BestPlaceNavigationAdaptiveLayoutTheme {
                val windowSize = calculateWindowSizeClass(this)
                BestPlacesApp(windowSize = windowSize.widthSizeClass)
            }
        }

    }
}