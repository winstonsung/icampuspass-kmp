package app.icampuspass.android.widget.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import app.icampuspass.android.widget.viewmodels.WidgetConfigurationScreenViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun WidgetConfigurationScreen(
    viewModel: WidgetConfigurationScreenViewModel = koinViewModel(),
    onButtonClick: () -> Unit = {}
) {
    WidgetConfigurationScreenContent(
        onButtonClick = onButtonClick
    )
}

@Composable
fun WidgetConfigurationScreenContent(
    onButtonClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text("Hello, world!")

        Button(
            onClick = onButtonClick
        ) {
            Text(text = "Text")
        }
    }
}
