package de.mpmediasoft.polyspiral.model

import kotlinx.datetime.Clock
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private val TWO_PI = 2 * PI
private val NUM_LINES = 150
private val DELTA_HUE = 360.0 / NUM_LINES

//private var oldInstant = Clock.System.now()

fun drawSpiral(g: VirtualDrawScope, length: Double, lengthIncrement: Double, angleIncrementDeg: Double, strokeWidth: Double) {
//    val newInstant = Clock.System.now()
//    println("drawSpiral: delta = ${newInstant - oldInstant}, angleIncrementDeg = %.2f".format(angleIncrementDeg))
//    oldInstant = newInstant
//
    var x1 = g.width / 2
    var y1 = g.height / 2
    var len = length
    val angleIncrementRad = angleIncrementDeg * PI / 180
    var angleRad = angleIncrementRad
    g.strokeWidth(strokeWidth)
    for (i in 0 until NUM_LINES) {
        g.strokeColorHsv(i * DELTA_HUE, 1.0, 1.0)
        val x2 = x1 + cos(angleRad) * len
        val y2 = y1 - sin(angleRad) * len
        g.drawLine(x1, y1, x2, y2)
        x1 = x2
        y1 = y2
        len += lengthIncrement
        angleRad = (angleRad + angleIncrementRad) % TWO_PI
    }
}
