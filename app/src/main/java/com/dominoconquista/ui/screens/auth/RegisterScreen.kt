package com.dominoconquista.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
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
import com.dominoconquista.ui.components.AvatarPicker
import com.dominoconquista.ui.components.PrimaryButton
import com.dominoconquista.ui.theme.DominoConquistaTheme
import com.dominoconquista.ui.theme.Ink
import com.dominoconquista.ui.theme.PlayerCoral

@Composable
fun RegisterScreen(
        onRegisterClick: (name: String, email: String, password: String, avatarIndex: Int) -> Unit,
onGoToLogin: () -> Unit,
onBack: () -> Unit
) {
var name by remember { mutableStateOf("") }
var email by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }
var confirm by remember { mutableStateOf("") }
var avatarIndex by remember { mutableStateOf(0) }

val passwordsMatch = password == confirm
val canRegister = name.isNotBlank() && email.isNotBlank() &&
        password.length >= 6 && passwordsMatch

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
Spacer(Modifier.height(8.dp))
Text("Crear cuenta", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Ink)
Text("Únete a Dominó Conquista", color = Ink)

Text("Elige tu avatar", fontWeight = FontWeight.Bold, color = Ink)
AvatarPicker(selectedIndex = avatarIndex, onSelected = { avatarIndex = it })

AppTextField(value = name, onValueChange = { name = it }, label = "Nombre de usuario")
AppTextField(value = email, onValueChange = { email = it }, label = "Correo electrónico")
AppTextField(
        value = password,
        onValueChange = { password = it },
label = "Contraseña (mínimo 6 caracteres)",
isPassword = true
        )
AppTextField(
        value = confirm,
        onValueChange = { confirm = it },
label = "Confirmar contraseña",
isPassword = true
        )

        if (confirm.isNotEmpty() && !passwordsMatch) {
Text("Las contraseñas no coinciden", color = PlayerCoral)
            }

PrimaryButton(
        text = "Registrarse",
        onClick = { onRegisterClick(name, email, password, avatarIndex) },
enabled = canRegister
            )
TextButton(
        onClick = onGoToLogin,
        modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
Text("¿Ya tienes cuenta? Inicia sesión")
            }
                    }
                    }
                    }

@Preview(showBackground = true)
@Composable
private fun RegisterScreenPreview() {
    DominoConquistaTheme {
        RegisterScreen(
                onRegisterClick = { _, _, _, _ -> },
                onGoToLogin = {},
                onBack = {}
        )
    }
}