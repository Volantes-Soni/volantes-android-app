package me.ernestzamelczyk.volantessoni.view

import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import me.ernestzamelczyk.volantessoni.view.base.BaseActivity
import me.ernestzamelczyk.volantessoni.view.login.LoginActivity
import me.ernestzamelczyk.volantessoni.view.main.MainActivity
import javax.inject.Inject

class StarterActivity : BaseActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(
                firebaseAuth.currentUser?.let {
                    MainActivity.getIntent(this)
                } ?: LoginActivity.getIntent(this)
        )
        finish()
    }


}