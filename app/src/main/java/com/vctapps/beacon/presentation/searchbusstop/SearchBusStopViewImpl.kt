package com.vctapps.beacon.presentation.searchbusstop

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class SearchBusStopViewImpl : BaseActivity(), SearchBusStopView {

    lateinit var presenter: SearchBusStopPresenter
    lateinit var searchingBusStopView: ViewGroup
    lateinit var foundBusStopView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchingBusStopView = linearlayout_searching_bus_stop
        foundBusStopView = linearlayout_found_bus_stop

        presenter = SearchBusStopPresenterImpl()
    }

    override fun onResume() {
        super.onResume()

        presenter.attachTo(this)
    }

    override fun onPause() {
        super.onPause()

        presenter.dettachFrom()
    }

    override fun onBeaconServiceConnect() {
        Timber.d("Beacon service connected. Trying locate a beacon")

        presenter.onBoundService()
    }

    override fun showLoading() {
        Timber.d("Show loading")
        searchingBusStopView.visibility = View.VISIBLE
        foundBusStopView.visibility = View.GONE
    }

    override fun hideLoading() {
        Timber.d("Hide loading")
        searchingBusStopView.visibility = View.GONE
        foundBusStopView.visibility = View.VISIBLE
    }
}
