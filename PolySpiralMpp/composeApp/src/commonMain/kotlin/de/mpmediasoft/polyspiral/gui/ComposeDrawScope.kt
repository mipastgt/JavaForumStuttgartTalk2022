package de.mpmediasoft.polyspiral.gui

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ExperimentalGraphicsApi
import androidx.compose.ui.graphics.drawscope.DrawScope
import de.mpmediasoft.polyspiral.model.VirtualDrawScope

class ComposeDrawScope(val drawScope: DrawScope): VirtualDrawScope {

    private var strokeColor = Color.Black
    private var strokeWidth = 1f

    override val width: Double
        get() = drawScope.size.width.toDouble()

    override val height: Double
        get() = drawScope.size.height.toDouble()

    @OptIn(ExperimentalGraphicsApi::class)
    override fun strokeColorHsv(hue: Double, saturation: Double, value: Double) {
        this.strokeColor = Color.hsv(hue.toFloat(), saturation.toFloat(), value.toFloat())
    }

    override fun strokeWidth(strokeWidth: Double) {
        this.strokeWidth = strokeWidth.toFloat()
    }

    override fun drawLine(startX: Double, startY: Double, endX: Double, endY: Double) {
        drawScope.drawLine(strokeColor, Offset(startX.toFloat(), startY.toFloat()), Offset(endX.toFloat(), endY.toFloat()), strokeWidth)
    }

}