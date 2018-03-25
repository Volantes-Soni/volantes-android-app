package me.ernestzamelczyk.volantessoni.view.calendar

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.ernestzamelczyk.volantessoni.R
import me.ernestzamelczyk.volantessoni.databinding.FragmentCalendarBinding
import me.ernestzamelczyk.volantessoni.view.base.BaseFragment
import javax.inject.Inject

class CalendarFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: CalendarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val calendarBinding = DataBindingUtil.inflate<FragmentCalendarBinding>(inflater, R.layout.fragment_calendar, container, false)
        calendarBinding.viewModel = viewModel
        return calendarBinding.root
    }

}