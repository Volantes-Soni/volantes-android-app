package me.ernestzamelczyk.volantessoni.view.login

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import io.reactivex.subjects.BehaviorSubject
import me.ernestzamelczyk.volantessoni.di.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class LoginViewModel @Inject constructor(private val firebaseAuth: FirebaseAuth) {

    val login: ObservableField<String> = ObservableField()
    val password: ObservableField<String> = ObservableField()
    val loginError: ObservableField<String?> = ObservableField()
    val passwordError: ObservableField<String?> = ObservableField()

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    val actionsSubject: BehaviorSubject<LoginAction> = BehaviorSubject.create()

    fun authenticate() {
        val login = login.get()
        val password = password.get()
        if(preconditions(login, password)) {
            isLoading.set(true)
            firebaseAuth
                    .signInWithEmailAndPassword(login, password)
                    .addOnCompleteListener {
                        isLoading.set(false)
                    }
                    .addOnSuccessListener {
                        actionsSubject.onNext(LoginAction.START_MAIN_ACTIVITY_AND_FINISH)
                    }.addOnFailureListener {
                        when (it) {
                            is FirebaseAuthInvalidUserException -> loginError.set("Nieprawidłowy e-mail")
                            is FirebaseAuthInvalidCredentialsException -> passwordError.set("Nieprawidłowe hasło")
                        }
                    }
        } else {
            if(login.isNullOrBlank()) {
                loginError.set("Wpisz adres e-mail")
            }
            if(password.isNullOrBlank()) {
                passwordError.set("Wpisz hasło")
            }
        }
    }

    fun onLoginInput() {
        loginError.set(null)
    }

    fun onPasswordInput() {
        passwordError.set(null)
    }

    private fun preconditions(login: String?, password: String?): Boolean {
        return !login.isNullOrBlank() && !password.isNullOrBlank()
    }

}