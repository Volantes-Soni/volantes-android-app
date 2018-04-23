package me.ernestzamelczyk.volantessoni.view.main

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.subjects.BehaviorSubject
import me.ernestzamelczyk.volantessoni.di.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    internal val actionsSubject: BehaviorSubject<MainAction> = BehaviorSubject.create()

    internal fun onNavigationItemSelected(navigationItem: MainNavigationItem?): Boolean {
//        MainAction.LOGOUT -> {
//            firebaseAuth.signOut()
//            actionsSubject.onNext(MainAction.START_LOGIN_ACTIVITY_AND_FINISH)
//        }
        if(navigationItem != null) {
            when (navigationItem) {
                MainNavigationItem.FEED -> {
                    actionsSubject.onNext(MainAction.SHOW_FEED_FRAGMENT)
                }
                MainNavigationItem.CALENDAR -> {
                    actionsSubject.onNext(MainAction.SHOW_CALENDAR_FRAGMENT)
                }
                MainNavigationItem.SETTINGS -> {
                    actionsSubject.onNext(MainAction.SHOW_SETTINGS_FRAGMENT)
                }
            }
            return true
        } else {
            return false
        }
    }

    internal fun onNavigationItemReselected(navigationItem: MainNavigationItem) {
        when(navigationItem) {
            MainNavigationItem.FEED -> {

            }
            MainNavigationItem.CALENDAR -> {

            }
            MainNavigationItem.SETTINGS -> {

            }
        }
    }

}