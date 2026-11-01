package com.dominoconquista.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.Ink
import com.dominoconquista.ui.theme.SurfaceWhite
import com.dominoconquista.ui.theme.TileBorder

@Composable
fun OptionSelector(
    options: List<Int>,
    selected: Int,
    onSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    labelFor: (Int) -> String = { it.toString() }
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        options.forEach { option ->
            val isSelected = option == selected
            Surface(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelected(option) },
                shape = RoundedCornerShape(12.dp),
                color = if (isSelected) Amber else SurfaceWhite,
                border = BorderStroke(
                    if (isSelected) 2.dp else 1.dp,
                    if (isSelected) Amber else TileBorder
                )
            ) {
                Box(
                    modifier = Modifier.padding(vertical = 14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(labelFor(option), fontWeight = FontWeight.Bold, color = Ink)
                }
            }
        }
    }
}