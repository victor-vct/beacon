package com.vctapps.beacon.presentation.requestbus

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.vctapps.beacon.R
import com.vctapps.beacon.core.BeaconApplication
import kotlinx.android.synthetic.main.activity_request_bus.*
import kotlinx.android.synthetic.main.loading_view.*
import kotlinx.android.synthetic.main.recyclerviewstate.*
import javax.inject.Inject

class RequestBusViewImpl : AppCompatActivity(), RequestBusView {

    companion object{
        val BUS_MODEL = "bus_model"
    }

    lateinit var loadingView: ViewGroup

    lateinit var favoriteButton: Button

    lateinit var cancelRequestButton: Button

    @Inject
    lateinit var presenter: RequestBusPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_bus)

        loadingView = loading_view

        favoriteButton = favoriteBus

        cancelRequestButton = cancelRequestBus

        favoriteButton.setOnClickListener({presenter.onClickedFavoriteButton()})

        cancelRequestButton.setOnClickListener({presenter.onClickedCancelButton()})

        (applicationContext as BeaconApplication).beaconComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.attachTo(this)

        presenter.processIntent(intent)
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

    override fun showMessageError() {
        error_message.visibility = View.VISIBLE
    }

    override fun getContext() = this

    override fun close() = finish()
}
