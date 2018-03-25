package me.ernestzamelczyk.volantessoni.view.login

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable
import me.ernestzamelczyk.volantessoni.R
import me.ernestzamelczyk.volantessoni.databinding.ActivityLoginBinding
import me.ernestzamelczyk.volantessoni.view.base.BaseActivity
import me.ernestzamelczyk.volantessoni.view.main.MainActivity
import javax.inject.Inject

class LoginActivity: BaseActivity() {

    @Inject lateinit var viewModel: LoginViewModel

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)
        binding.viewModel = viewModel
    }

    private fun onAction(action: LoginAction) = when(action) {
        LoginAction.START_MAIN_ACTIVITY_AND_FINISH -> {
            startActivity(MainActivity.getIntent(this))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(viewModel.actionsSubject.subscribe(::onAction))
    }

    override fun onPause() {
        compositeDisposable.clear()
        super.onPause()
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }

}