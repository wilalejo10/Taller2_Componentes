package com.dominoconquista

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dominoconquista.ui.screens.game.FakeData
import com.dominoconquista.ui.screens.game.GameScreen
import com.dominoconquista.ui.theme.DominoConquistaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DominoConquistaTheme {
                GameScreen(
                    state = FakeData.gameState(),
                    onTileSelected = {},
                    onCellClick = { _, _ -> },
                    onPlace = {},
                    onDraw = {},
                    onLeave = {}
                )
            }
        }
    }
}