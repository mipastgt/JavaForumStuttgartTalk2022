package de.mpmediasoft.polyspiral.gui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
actual fun DataTooltipArea(
    dataString: String,
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    TooltipArea(
        modifier = modifier,
        tooltip = {
            Surface(
                modifier = Modifier.shadow(4.dp),
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(
                    text = dataString,
                    modifier = Modifier.padding(10.dp)
                )
            }
        },
        delayMillis = 500, // in milliseconds
        tooltipPlacement = TooltipPlacement.CursorPoint(offset = DpOffset(16.dp, 16.dp)),
        content = content
    )
}

