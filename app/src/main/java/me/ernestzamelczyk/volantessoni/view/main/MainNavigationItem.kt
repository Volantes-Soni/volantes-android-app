package me.ernestzamelczyk.volantessoni.view.main

import android.support.annotation.IdRes
import me.ernestzamelczyk.volantessoni.R

internal enum class MainNavigationItem {
    FEED,
    CALENDAR,
    SETTINGS;

    companion object {
        fun fromId(@IdRes id: Int): MainNavigationItem? {
            return when(id) {
                R.id.menu_feed -> FEED
                R.id.menu_calendar -> CALENDAR
                R.id.menu_settings -> SETTINGS
                else -> null
            }
        }
    }

}