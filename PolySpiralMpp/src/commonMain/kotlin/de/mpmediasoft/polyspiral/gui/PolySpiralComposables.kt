package de.mpmediasoft.polyspiral.gui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun DataTooltipArea(
    dataString: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
)
