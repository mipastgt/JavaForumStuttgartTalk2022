import androidx.compose.material.MaterialTheme
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.singleWindowApplication
import de.mpmediasoft.polyspiral.gui.PolySpiralApp
import java.util.*

fun main() {
    singleWindowApplication(
        title = "PolySpiral - Compose - Desktop - JVM",
        state = WindowState(size = DpSize(640.dp, 800.dp))
    ) {
        Locale.setDefault(Locale.US)
        MaterialTheme {
            PolySpiralApp()
        }
    }
}