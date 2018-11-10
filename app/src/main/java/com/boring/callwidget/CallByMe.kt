package com.boring.callwidget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import java.lang.System.exit

/**
 * Implementation of App Widget functionality.
 */
class CallByMe : AppWidgetProvider() {

    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        super.onEnabled(context)

    }

    override fun onDisabled(context: Context) {
        super.onDisabled(context)
    }

    companion object {

        internal fun updateAppWidget(context: Context, appWidgetManager: AppWidgetManager,
                                     appWidgetId: Int) {

            val views = RemoteViews(context.packageName, R.layout.call_by_me)
            var intent = Intent(context, First::class.java)
            var pendingevent = PendingIntent.getActivity(context, 100, intent, 0)
            views.setOnClickPendingIntent(R.id.button, pendingevent)
            // Instruct the widget manager to update the widget
            //views.setOnClickPendingIntent(R.id.button, )
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

}

