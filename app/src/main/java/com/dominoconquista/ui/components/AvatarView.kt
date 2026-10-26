package com.dominoconquista.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dominoconquista.ui.model.Avatars
import com.dominoconquista.ui.theme.CellNormal
import com.dominoconquista.ui.theme.TileBorder

@Composable
fun AvatarView(
    avatarIndex: Int,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
    backgroundColor: Color = CellNormal,
    borderColor: Color = TileBorder,
    borderWidth: Dp = 2.dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(borderWidth, borderColor, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(Avatars.emoji(avatarIndex), fontSize = (size.value * 0.55f).sp)
    }
}