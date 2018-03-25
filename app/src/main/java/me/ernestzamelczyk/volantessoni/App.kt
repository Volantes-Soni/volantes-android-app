package me.ernestzamelczyk.volantessoni

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import me.ernestzamelczyk.volantessoni.di.DaggerAppComponent

class App: DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().build()
    }

}