package de.mpmediasoft.polyspiral.model

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PolySpiralManagerState (
    val isRendering: Boolean = false,
    val delayMillis: Long = 40,
    val length: Double = 5.0,
    val lengthIncrement: Double = 3.0,
    val strokeWidth: Double = 2.0,
    val angleIncrementDeg: Double = 0.0
)

class PolySpiralManager(val coroutineScope: CoroutineScope) {

    private var timerJob: Job? = null

    private val _polySpiralManagerState = MutableStateFlow(PolySpiralManagerState())
    val polySpiralManagerState = _polySpiralManagerState.asStateFlow()

    val isRendering: Boolean
        get() = timerJob != null

    val isNotRendering: Boolean
        get() = timerJob == null

    var angleIncrementDeg: Double
        get() = polySpiralManagerState.value.angleIncrementDeg
        set(value) = _polySpiralManagerState.update { s -> s.copy(angleIncrementDeg = value.coerceIn(0.0, 360.0)) }

    fun toggleRendering() {
        if (isNotRendering) {
            startRendering()
        } else {
            stopRendering()
        }
    }

    fun startRendering() {
        if (isNotRendering) {
            timerJob = coroutineScope.launch {
                _polySpiralManagerState.update { s -> s.copy(isRendering = isRendering) }
                while (true) {
                    with (polySpiralManagerState.value) {
                        _polySpiralManagerState.update { s -> s.copy(angleIncrementDeg = (angleIncrementDeg + 0.05) % 360.0) }
                        delay(delayMillis)
                    }
                }
            }
        }
    }

    fun stopRendering() {
        if (isRendering) {
            timerJob?.cancel()
            timerJob = null
            _polySpiralManagerState.update { s -> s.copy(isRendering = isRendering) }
        }
    }

    fun reset() {
        _polySpiralManagerState.update { PolySpiralManagerState().copy(isRendering = isRendering) }
    }

}
