package com.vctapps.beacon.presentation.searchbusstop

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import com.vctapps.beacon.R
import com.vctapps.beacon.core.BeaconApplication
import com.vctapps.beacon.core.presentation.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.recyclerviewstate.*
import timber.log.Timber
import javax.inject.Inject

class SearchBusStopViewImpl : BaseActivity(), SearchBusStopView {

    @Inject
    lateinit var presenter: SearchBusStopPresenter

    lateinit var searchingBusStopView: ViewGroup

    lateinit var foundBusStopView: ViewGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchingBusStopView = linearlayout_searching_bus_stop
        foundBusStopView = linearlayout_found_bus_stop

        (applicationContext as BeaconApplication).beaconComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.attachTo(this)
    }

    override fun onPause() {
        super.onPause()

        presenter.dettachFrom()
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

    override fun showMessageError() {
        error_message.visibility = View.VISIBLE
    }

    override fun showBluetoothNotEnable() {
        var builder = AlertDialog.Builder(this)

        builder.setTitle(getString(R.string.enable_bluetooth_dialog_title))
                .setMessage(getString(R.string.enable_bluetooth_dialog_message))
                .setPositiveButton(getString(R.string.button_positive),
                        { _, _ -> presenter.onClickedToEnableBluetooth() })

        builder.show()
    }
}
