package app.icampuspass.android.widget

import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import app.icampuspass.android.shared.views.theme.Theme
import app.icampuspass.android.widget.viewmodels.WidgetConfigurationScreenViewModel
import app.icampuspass.android.widget.views.screens.WidgetConfigurationScreen
import org.koin.androidx.compose.koinViewModel

@Composable
fun WidgetConfiguration(
    viewModel: ViewModel = koinViewModel<WidgetConfigurationScreenViewModel>(),
    onButtonClick: () -> Unit = {}
) {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                WidgetConfigurationScreen(
                    onButtonClick = onButtonClick
                )
            }
        }
    }
}
