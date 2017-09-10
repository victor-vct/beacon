package com.vctapps.beacon.presentation.detailbus.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.vctapps.beacon.R
import com.vctapps.beacon.core.BeaconApplication
import com.vctapps.beacon.core.presentation.BaseActivity
import com.vctapps.beacon.presentation.detailbus.presenter.DetailBusPresenter
import com.vctapps.beacon.presentation.detailbus.presenter.DetailBusPresenterImpl
import com.vctapps.beacon.presentation.model.BusModelView
import com.vctapps.beacon.presentation.requestbus.RequestBusViewImpl
import kotlinx.android.synthetic.main.activity_detail_bus_view_impl.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class DetailBusViewImpl : BaseActivity(), DetailBusView {

    companion object {
        val BUS_VIEW_MODEL = "bus_view_model"
    }

    @Inject
    lateinit var presenter: DetailBusPresenter

    lateinit var busName: TextView
    lateinit var busOrigin: TextView
    lateinit var busDestiny: TextView
    lateinit var busArriveAt: TextView

    lateinit var requestBusButton: Button
    lateinit var backButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_bus_view_impl)

        var toolbar = toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)

        busName = detail_bus_name
        busOrigin = detail_bus_origin
        busDestiny = detail_bus_destiny
        busArriveAt = detail_bus_arrive_at

        requestBusButton = requestBus
        backButton = backFromDetail

        requestBusButton.setOnClickListener({ presenter.onRequestBusClicked() })
        backButton.setOnClickListener({ finish() })

        (applicationContext as BeaconApplication).beaconComponent.inject(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.attachTo(this)

        presenter.processIntent(intent)
    }

    override fun onPause() {
        super.onPause()

        presenter.dettachFrom()
    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun loadInfos(bus: BusModelView) {
        busName.text = bus.name
        busOrigin.text = bus.origin
        busDestiny.text = bus.destiny
        busArriveAt.text = bus.arriveAt
    }

    override fun showMessageError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToRequestBus() {
        var goToRequestBus = Intent(this, RequestBusViewImpl::class.java)

        startActivity(goToRequestBus)
    }
}
