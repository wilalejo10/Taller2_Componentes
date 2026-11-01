package com.dominoconquista.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.theme.Ink

@Composable
fun MenuCard(
        title: String,
        subtitle: String,
        color: Color,
        onClick: () -> Unit,
modifier: Modifier = Modifier
) {
Card(
        shape = RoundedCornerShape(16.dp),
colors = CardDefaults.cardColors(containerColor = color),
modifier = modifier
        .fillMaxWidth()
            .clickable { onClick() }
    ) {
Column(Modifier.padding(18.dp)) {
Text(title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Ink)
Text(subtitle, color = Ink)
        }
                }
                }