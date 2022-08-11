import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import de.mpmediasoft.polyspiral.gui.PolySpiralApp
import org.jetbrains.skiko.wasm.onWasmReady

fun main() {
    onWasmReady {
        BrowserViewportWindow("PolySpiral - Compose - JS") {
            MaterialTheme {
                PolySpiralApp()
            }
        }
    }
}

