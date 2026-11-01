package com.dominoconquista.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.components.AppTextField
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink

@Composable
fun LoginScreen(
        onLoginClick: (email: String, password: String) -> Unit,
onGoogleClick: () -> Unit,
onGoToRegister: () -> Unit,
onBack: () -> Unit
) {
var email by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }

Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier.fillMaxSize()
    ) {
Column(
        modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        .padding(24.dp),
verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
TextButton(onClick = onBack) { Text("← Volver") }
Spacer(Modifier.height(16.dp))
Text("Bienvenido", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Ink)
Text("Inicia sesión para continuar", color = Ink)
Spacer(Modifier.height(8.dp))

AppTextField(
        value = email,
        onValueChange = { email = it },
label = "Correo electrónico"
        )
AppTextField(
        value = password,
        onValueChange = { password = it },
label = "Contraseña",
isPassword = true
        )

PrimaryButton(
        text = "Iniciar sesión",
        onClick = { onLoginClick(email, password) },
enabled = email.isNotBlank() && password.isNotBlank()
            )
OutlinedButton(
        onClick = onGoogleClick,
        shape = RoundedCornerShape(14.dp),
modifier = Modifier
        .fillMaxWidth()
                    .height(52.dp)
            ) {
Text("Continuar con Google")
            }
TextButton(
        onClick = onGoToRegister,
        modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
Text("¿No tienes cuenta? Regístrate")
            }
                    }
                    }
                    }

@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    DominoConquistaTheme {
        LoginScreen(
                onLoginClick = { _, _ -> },
                onGoogleClick = {},
                onGoToRegister = {},
                onBack = {}
        )
    }
}