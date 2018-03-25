package me.ernestzamelczyk.volantessoni.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.ernestzamelczyk.volantessoni.di.scopes.ActivityScoped
import me.ernestzamelczyk.volantessoni.view.StarterActivity
import me.ernestzamelczyk.volantessoni.view.login.LoginActivity
import me.ernestzamelczyk.volantessoni.view.main.MainActivity
import me.ernestzamelczyk.volantessoni.view.main.MainModule

@Module
interface ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = [
        MainModule::class
    ])
    fun mainActivity(): MainActivity

    @ActivityScoped
    @ContributesAndroidInjector()
    fun loginActivity(): LoginActivity

    @ActivityScoped
    @ContributesAndroidInjector()
    fun splashActivity(): StarterActivity

}