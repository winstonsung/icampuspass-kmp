package app.icampuspass.android.widget

import android.appwidget.AppWidgetManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.glance.appwidget.GlanceAppWidgetManager
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class AndroidAppWidgetConfigurationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(scrim = Color.Black.toArgb()),
            navigationBarStyle = SystemBarStyle.dark(scrim = Color.Black.toArgb())
        )

        super.onCreate(savedInstanceState)

        setResult(RESULT_CANCELED)

        val appWidgetId = intent?.getIntExtra(
            AppWidgetManager.EXTRA_APPWIDGET_ID,
            AppWidgetManager.INVALID_APPWIDGET_ID
        ) ?: AppWidgetManager.INVALID_APPWIDGET_ID

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish()

            return
        }

        val resultValue =
            Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)

        setContent {
            WidgetConfiguration(
                onButtonClick = {
                    lifecycleScope.launch {
                        val context = this@AndroidAppWidgetConfigurationActivity
                        val glanceId = GlanceAppWidgetManager(context).getGlanceIdBy(appWidgetId)

                        AndroidAppWidget().update(context = context, id = glanceId)
                    }

                    setResult(RESULT_OK, resultValue)
                    finish()
                }
            )
        }
    }
}
