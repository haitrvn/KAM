import androidx.compose.runtime.collectAsState
import androidx.compose.ui.window.ComposeUIViewController
import com.haitrvn.kamsample.App
import com.haitrvn.kamsample.SampleViewModel
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    val viewModel = SampleViewModel()
    App(viewModel.state.collectAsState())
}
