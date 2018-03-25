package me.ernestzamelczyk.volantessoni.view.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import io.reactivex.disposables.CompositeDisposable
import me.ernestzamelczyk.volantessoni.R
import me.ernestzamelczyk.volantessoni.databinding.ActivityMainBinding
import me.ernestzamelczyk.volantessoni.view.base.BaseActivity
import me.ernestzamelczyk.volantessoni.view.calendar.CalendarFragment
import me.ernestzamelczyk.volantessoni.view.login.LoginActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.bottomNavigationView.setOnNavigationItemReselectedListener(::onNavigationItemReselected)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(::onNavigationItemSelected)
    }

    override fun onResume() {
        super.onResume()
        compositeDisposable.add(viewModel.actionsSubject.subscribe(::onAction))
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    private fun onAction(action: MainAction) {
        when (action) {
            MainAction.START_LOGIN_ACTIVITY_AND_FINISH -> {
                startActivity(LoginActivity.getIntent(this))
                finish()
            }
            MainAction.DISPLAY_CALENDAR_FRAGMENT -> {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_frame, CalendarFragment())
                        .commit()
            }
            else -> {}
        }
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean {
        return viewModel.onNavigationItemSelected(
                when (item.itemId) {
                    R.id.menu_logout -> MainAction.LOGOUT
                    else -> null
                }
        )
    }

    private fun onNavigationItemReselected(item: MenuItem) {
        viewModel.onNavigationItemReselected(
                when (item.itemId) {
                    R.id.menu_logout -> MainAction.LOGOUT
                    else -> null
                }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
