package de.mpmediasoft.polyspiral.gui

import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun DataTooltipArea(
    dataString: String,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    Row(modifier = modifier) {content()}
}
