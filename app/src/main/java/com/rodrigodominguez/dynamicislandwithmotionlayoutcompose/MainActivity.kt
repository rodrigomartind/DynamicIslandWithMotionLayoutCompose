package com.rodrigodominguez.dynamicislandwithmotionlayoutcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland.DynamicIsland
import com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland.DynamicIslandDemo2
import com.rodrigodominguez.dynamicislandwithmotionlayoutcompose.dynamicisland.DynamicIslandDemo3

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                DynamicIsland()
                DynamicIslandDemo2()
                DynamicIslandDemo3()
            }
        }
    }
}