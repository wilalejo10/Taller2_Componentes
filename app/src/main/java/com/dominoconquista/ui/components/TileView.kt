package com.dominoconquista.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.TileBody
import com.dominoconquista.ui.theme.TileBorder

@Composable
fun TileView(
    left: Int,
    right: Int,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        color = TileBody,
        border = BorderStroke(
            if (selected) 3.dp else 1.dp,
            if (selected) Amber else TileBorder
        ),
        shadowElevation = if (selected) 6.dp else 1.dp
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(left.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Box(
                Modifier
                    .width(1.dp)
                    .height(20.dp)
                    .background(TileBorder)
            )
            Text(right.toString(), fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}
