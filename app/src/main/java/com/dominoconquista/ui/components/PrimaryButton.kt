package com.dominoconquista.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.Ink

@Composable
fun PrimaryButton(
        text: String,
        onClick: () -> Unit,
modifier: Modifier = Modifier,
enabled: Boolean = true
        ) {
Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(14.dp),
colors = ButtonDefaults.buttonColors(containerColor = Amber, contentColor = Ink),
modifier = modifier
        .fillMaxWidth()
            .height(52.dp)
    ) {
Text(text, fontWeight = FontWeight.Bold)
    }
            }