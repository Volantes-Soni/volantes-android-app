package me.ernestzamelczyk.volantessoni.view.main

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.ernestzamelczyk.volantessoni.di.scopes.FragmentScoped
import me.ernestzamelczyk.volantessoni.view.calendar.CalendarFragment
import me.ernestzamelczyk.volantessoni.view.sheets.SheetsFragment

@Module
abstract class MainModule {

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun calendarFragment(): CalendarFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun sheetsFragment(): SheetsFragment

}