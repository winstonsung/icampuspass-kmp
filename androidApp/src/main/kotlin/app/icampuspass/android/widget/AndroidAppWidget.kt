package app.icampuspass.android.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent

class AndroidAppWidget : GlanceAppWidget() {
    override suspend fun provideGlance(
        context: Context,
        id: GlanceId
    ) {
        // In this method, load data needed to render the AppWidget.
        // Use `withContext` to switch to another thread for long running
        // operations.

        provideContent {
            Widget()
        }
    }

    override suspend fun providePreview(
        context: Context,
        widgetCategory: Int
    ) {
        provideContent {
            WidgetPreview()
        }
    }
}
