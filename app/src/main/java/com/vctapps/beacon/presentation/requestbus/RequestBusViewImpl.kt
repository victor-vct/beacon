package com.vctapps.beacon.presentation.requestbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.vctapps.beacon.R
import com.vctapps.beacon.core.BeaconApplication
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.recyclerviewstate.*
import javax.inject.Inject

class RequestBusViewImpl : AppCompatActivity(), RequestBusView {

    lateinit var loadingView: ViewGroup

    @Inject
    lateinit var presenter: RequestBusPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_bus)

        loadingView = loading_view

        (applicationContext as BeaconApplication).beaconComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.attachTo(this)
    }

    override fun onPause() {
        presenter.dettachFrom()

        super.onPause()
    }

    override fun showLoading() {
        loadingView.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        loadingView.visibility = View.GONE
    }

}
