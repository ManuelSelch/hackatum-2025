import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitViewController
import androidx.compose.ui.window.ComposeUIViewController
import org.example.project.App
import org.example.project.AppContainer
import platform.UIKit.UIViewController

fun ComposeEntryPointWithUIViewController(
    createUIViewController: () -> UIViewController
): UIViewController =
    ComposeUIViewController() {
        AppContainer {
            UIKitViewController(
                factory = createUIViewController,
                modifier = Modifier.height(200.dp).width(200.dp)
            )
        }
    }