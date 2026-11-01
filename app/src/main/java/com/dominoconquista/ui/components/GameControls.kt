package com.dominoconquista.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GameControls(
    canPlace: Boolean,
    onPlace: () -> Unit,
    onDraw: () -> Unit,
    onLeave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Button(onClick = onPlace, enabled = canPlace, modifier = Modifier.weight(1f)) {
            Text("Colocar")
        }
        OutlinedButton(onClick = onDraw, modifier = Modifier.weight(1f)) {
            Text("Robar")
        }
        OutlinedButton(onClick = onLeave, modifier = Modifier.weight(1f)) {
            Text("Salir")
        }
    }
}
