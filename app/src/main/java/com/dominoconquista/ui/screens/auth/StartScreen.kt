package com.dominoconquista.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink

@Composable
fun StartScreen(
        onLogin: () -> Unit,
onRegister: () -> Unit
) {
Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
Column(
        modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
verticalArrangement = Arrangement.Center,
horizontalAlignment = Alignment.CenterHorizontally
        ) {
Text("🎲", fontSize = 64.sp)
Spacer(Modifier.height(8.dp))
Text("Dominó Conquista", fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Ink)
Text("Conquista el tablero ficha por ficha", color = Ink)
Spacer(Modifier.height(40.dp))
PrimaryButton(text = "Iniciar sesión", onClick = onLogin)
Spacer(Modifier.height(12.dp))
OutlinedButton(
        onClick = onRegister,
        shape = RoundedCornerShape(14.dp),
modifier = Modifier
        .fillMaxWidth()
                    .height(52.dp)
            ) {
Text("Crear cuenta")
            }
                    }
                    }
                    }

@Preview(showBackground = true)
@Composable
private fun StartScreenPreview() {
    DominoConquistaTheme { StartScreen(onLogin = {}, onRegister = {}) }
}