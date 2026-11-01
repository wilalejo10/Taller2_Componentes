package com.dominoconquista.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dominoconquista.ui.model.Avatars
import com.dominoconquista.ui.theme.Amber
import com.dominoconquista.ui.theme.CellNormal
import com.dominoconquista.ui.theme.TileBorder

@Composable
fun AvatarPicker(
        selectedIndex: Int,
        onSelected: (Int) -> Unit,
modifier: Modifier = Modifier
) {
LazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
itemsIndexed(Avatars.all) { index, _ ->
        val isSelected = index == selectedIndex
    AvatarView(
            avatarIndex = index,
            size = 56.dp,
            backgroundColor = if (isSelected) Amber.copy(alpha = 0.4f) else CellNormal,
            borderColor = if (isSelected) Amber else TileBorder,
            borderWidth = if (isSelected) 4.dp else 1.dp,
            modifier = Modifier.clickable { onSelected(index) }
            )
}
    }
            }