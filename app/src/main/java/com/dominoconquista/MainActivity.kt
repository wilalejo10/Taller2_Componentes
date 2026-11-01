package com.dominoconquista

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dominoconquista.ui.navigation.AppNavHost
import com.dominoconquista.ui.theme.DominoConquistaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DominoConquistaTheme {
                AppNavHost()
            }
        }
    }
}