import androidx.compose.ui.window.ComposeUIViewController
import com.haitrvn.kamsample.App
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController { App() }
