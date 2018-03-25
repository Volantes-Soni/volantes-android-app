package me.ernestzamelczyk.volantessoni.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.ernestzamelczyk.volantessoni.App
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    ActivityBindingModule::class,
    AppModule::class
])
interface AppComponent: AndroidInjector<App>