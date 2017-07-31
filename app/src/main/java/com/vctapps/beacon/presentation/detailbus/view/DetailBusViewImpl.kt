package com.vctapps.beacon.presentation.detailbus.view

import android.os.Bundle
import android.widget.TextView
import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseActivity
import com.vctapps.beacon.presentation.detailbus.presenter.DetailBusPresenter
import com.vctapps.beacon.presentation.detailbus.presenter.DetailBusPresenterImpl
import com.vctapps.beacon.presentation.model.BusModelView
import kotlinx.android.synthetic.main.activity_detail_bus_view_impl.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailBusViewImpl : BaseActivity(), DetailBusView {

    companion object {
        val BUS_VIEW_MODEL = "bus_view_model"
    }

    lateinit var presenter: DetailBusPresenter

    lateinit var busName: TextView
    lateinit var busOrigin: TextView
    lateinit var busDestiny: TextView
    lateinit var busArriveAt: TextView

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

        presenter = DetailBusPresenterImpl()
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
}
