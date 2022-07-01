package de.mpmediasoft.polyspiral.model

interface VirtualDrawScope {

    val width: Double

    val height: Double

    fun strokeColorHsv(hue: Double, saturation: Double, value: Double)

    fun strokeWidth(strokeWidth: Double)

    fun drawLine(
        startX: Double,
        startY: Double,
        endX: Double,
        endY: Double
    )

}