import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import de.mpmediasoft.polyspiral.gui.PolySpiralApp
import platform.AppKit.NSApp
import platform.AppKit.NSApplication

fun main() {
    NSApplication.sharedApplication()
    Window("PolySpiral - Compose - Desktop - native") {
        MaterialTheme {
            PolySpiralApp()
        }
    }
    NSApp?.run()
}
