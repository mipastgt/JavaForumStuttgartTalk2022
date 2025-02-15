package de.mpmediasoft.polyspiral.gui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.mpmediasoft.polyspiral.model.PolySpiralManager
import de.mpmediasoft.polyspiral.model.drawSpiral

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PolySpiralApp(topOffset: Dp = 0.dp) {
    val coroutineScope = rememberCoroutineScope()
    val polySpiralManager = remember { PolySpiralManager(coroutineScope) }
    val polySpiralManagerState by polySpiralManager.polySpiralManagerState.collectAsState()
    val uiScale = LocalDensity.current.density

    val backgroundColor = Color(210, 230, 255)

    Surface {
        Column(modifier = Modifier.fillMaxSize()) {
            if (topOffset != 0.dp) {
                // To skip upper part of screen for iOS.
                Box(modifier = Modifier
                    .height(topOffset)
                    .background(backgroundColor)
                    .fillMaxWidth())
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                modifier = Modifier
                    .background(backgroundColor)
                    .padding(start = 10.dp, top = 5.dp, end = 10.dp, bottom = 5.dp)
                    .fillMaxWidth()
            ) {
//                DataTooltipArea("Toggle start/stop of the graphics animation.") {
                    Button(
                        onClick = {
                            if (polySpiralManagerState.isRendering) {
                                polySpiralManager.stopRendering()
                            } else {
                                polySpiralManager.startRendering()
                            }
                        },
    //                    modifier = Modifier.width(90.dp)
                    ) {
                        Text(text = if (polySpiralManagerState.isRendering) "Stop" else "Start")
                    }
//                }

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(65.dp, 36.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color.White)
                ) {
//                    DataTooltipArea("Show the current value of the angle in degrees.") {
                        Text(
//                              text = "%.2fº".format(polySpiralManagerState.angleIncrementDeg)
                            text = "${polySpiralManagerState.angleIncrementDeg.toInt()}º"
                        )
//                    }
                }

//                DataTooltipArea("Adjust the angle manually.", modifier = Modifier.weight(1f)) {
                    Slider(
                        value = polySpiralManagerState.angleIncrementDeg.toFloat(),
                        valueRange = 0f..360f,
                        onValueChange = { polySpiralManager.angleIncrementDeg = it.toDouble() },
                        modifier = Modifier.weight(1f)
                    )
//                }

//                DataTooltipArea("Stop the graphics animation.") {
                    Button(
                        onClick = { polySpiralManager.reset() },
//                    modifier = Modifier.width(90.dp)
                    ) {
                        Text(text = "Reset")
                    }
//                }
            }

            Canvas(modifier = Modifier.fillMaxSize().clipToBounds().background(Color.White)) {
                with (polySpiralManagerState) {
                    drawSpiral(ComposeDrawScope(this@Canvas), length * uiScale, lengthIncrement * uiScale, angleIncrementDeg, strokeWidth * uiScale)
                }
            }
        }
    }
}
