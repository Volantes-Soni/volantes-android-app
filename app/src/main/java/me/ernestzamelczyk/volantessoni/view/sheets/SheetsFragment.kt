package me.ernestzamelczyk.volantessoni.view.sheets

import android.databinding.DataBindingUtil
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_sheets.*
import me.ernestzamelczyk.volantessoni.R
import me.ernestzamelczyk.volantessoni.databinding.FragmentSheetsBinding
import me.ernestzamelczyk.volantessoni.view.base.BaseFragment
import javax.inject.Inject

class SheetsFragment: BaseFragment() {

    @Inject lateinit var viewModel: SheetsViewModel

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = DataBindingUtil.inflate<FragmentSheetsBinding>(
                inflater,
                R.layout.fragment_sheets,
                container,
                false)

        return binding.root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SheetsAdapter()
        recyclerView.adapter = adapter
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            recyclerView.layoutManager = LinearLayoutManager(context)
        } else {
            recyclerView.layoutManager = LinearLayoutManager(activity)
        }
        viewModel.getSheets().subscribe(adapter::updateItems)
    }

}