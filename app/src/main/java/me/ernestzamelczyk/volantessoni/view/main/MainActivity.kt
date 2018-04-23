package me.ernestzamelczyk.volantessoni.view.main

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.MenuItem
import dagger.Lazy
import io.reactivex.disposables.CompositeDisposable
import me.ernestzamelczyk.volantessoni.R
import me.ernestzamelczyk.volantessoni.databinding.ActivityMainBinding
import me.ernestzamelczyk.volantessoni.view.base.BaseActivity
import me.ernestzamelczyk.volantessoni.view.calendar.CalendarFragment
import me.ernestzamelczyk.volantessoni.view.login.LoginActivity
import me.ernestzamelczyk.volantessoni.view.sheets.SheetsFragment
import javax.inject.Inject

class MainActivity : BaseActivity() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    @Inject lateinit var viewModel: MainViewModel
    private val calendarFragment: Lazy<CalendarFragment> = Lazy { CalendarFragment() }
    private val sheetsFragment: Lazy<SheetsFragment> = Lazy { SheetsFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.bottomNavigationView.setOnNavigationItemReselectedListener(::onNavigationItemReselected)
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(::onNavigationItemSelected)
        setActionBar(binding.mainToolbar)
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
            MainAction.SHOW_CALENDAR_FRAGMENT -> {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_frame, calendarFragment.get())
                        .commit()
            }
            MainAction.SHOW_FEED_FRAGMENT -> {
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.content_frame, sheetsFragment.get())
                        .commit()
            }
            MainAction.SHOW_SETTINGS_FRAGMENT -> {

            }
            MainAction.LOGOUT -> {

            }
        }
    }

    private fun onNavigationItemSelected(item: MenuItem): Boolean =
        viewModel.onNavigationItemSelected(MainNavigationItem.fromId(item.itemId))

    private fun onNavigationItemReselected(item: MenuItem) {
        MainNavigationItem.fromId(item.itemId)?.let {
            viewModel.onNavigationItemReselected(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object {
        fun getIntent(context: Context): Intent = Intent(context, MainActivity::class.java)
    }
}
