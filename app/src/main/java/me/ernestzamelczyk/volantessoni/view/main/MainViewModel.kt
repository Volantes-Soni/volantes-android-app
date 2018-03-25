package me.ernestzamelczyk.volantessoni.view.main

import com.google.firebase.auth.FirebaseAuth
import io.reactivex.subjects.BehaviorSubject
import me.ernestzamelczyk.volantessoni.di.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MainViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    val actionsSubject: BehaviorSubject<MainAction> = BehaviorSubject.create()

    fun onNavigationItemSelected(action: MainAction?): Boolean = when(action) {
        MainAction.LOGOUT -> {
            firebaseAuth.signOut()
            actionsSubject.onNext(MainAction.START_LOGIN_ACTIVITY_AND_FINISH)
            true
        }
        else -> false
    }

    fun onNavigationItemReselected(action: MainAction?) {
        when(action) {
            MainAction.LOGOUT -> {
                firebaseAuth.signOut()
                actionsSubject.onNext(MainAction.START_LOGIN_ACTIVITY_AND_FINISH)
            }
            MainAction.START_LOGIN_ACTIVITY_AND_FINISH -> TODO()
            MainAction.DISPLAY_CALENDAR_FRAGMENT -> TODO()
            null -> TODO()
        }
    }

}