package app.icampuspass.android.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.components.CircleIconButton
import androidx.glance.appwidget.components.Scaffold
import androidx.glance.appwidget.components.TitleBar
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.text.Text
import androidx.glance.layout.Column
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.text.TextStyle
import app.icampuspass.android.R
import app.icampuspass.android.widgets.views.theme.Theme

@Composable
fun Widget() {
    val context = LocalContext.current

    Theme {
        Scaffold(
            backgroundColor = GlanceTheme.colors.widgetBackground,
            modifier = GlanceModifier.padding(
                top = if (true) {
                    0.dp
                } else {
                    12.dp
                },
                bottom = 12.dp,
            ),
            titleBar = if (true) {
                {
                    TitleBar(
                        startIcon = ImageProvider(resId = R.drawable.ic_launcher_round),
                        title = context.getString(R.string.app_widget_label),
                        iconColor = null,
                        textColor = GlanceTheme.colors.onSurface,
                        actions = {
                            CircleIconButton(
                                imageProvider =
                                    ImageProvider(resId = R.drawable.material_icon_open_in_new),
                                contentDescription = "",
                                contentColor = GlanceTheme.colors.onSurface,
                                backgroundColor = null,
                                onClick = {},
                            )
                        }
                    )
                }
            } else {
                null
            },
        ) {
            Column(
                modifier = GlanceModifier
                    .fillMaxSize()
                    .background(colorProvider = GlanceTheme.colors.surface)
                    .padding(all = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Hello, world!",
                    style = TextStyle(color = GlanceTheme.colors.onSurface)
                )
            }
        }
    }
}

@Composable
fun WidgetPreview() {
    Widget()
}
