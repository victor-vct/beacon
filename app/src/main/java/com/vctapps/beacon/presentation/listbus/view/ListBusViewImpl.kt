package com.vctapps.beacon.presentation.listbus.view

import android.os.Bundle
import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseActivity
import com.vctapps.beacon.core.presentation.RecyclerViewState
import com.vctapps.beacon.core.presentation.RecyclerViewState.State
import com.vctapps.beacon.presentation.listbus.model.BusModelView
import com.vctapps.beacon.presentation.listbus.presenter.ListBusPresenterImpl
import com.vctapps.beacon.presentation.listbus.presenter.ListBusPresenter
import kotlinx.android.synthetic.main.activity_bus_list_view_impl.*
import kotlinx.android.synthetic.main.toolbar.*

class ListBusViewImpl : BaseActivity(), ListBusView, ListBusItemAdapter.OnClickBus {

    lateinit var recyclerBusList: RecyclerViewState
    val presenter: ListBusPresenter = ListBusPresenterImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_list_view_impl)
        var toolbar = toolbar
        setSupportActionBar(toolbar)

        recyclerBusList = recyclerview_list_bus
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
        recyclerBusList.setState(State.LOADING)
    }

    override fun hideLoading() {
        recyclerBusList.setState(State.EMPTY)
    }

    override fun loadList(listBusModelView: MutableList<BusModelView>) {
        var adapter = ListBusItemAdapter(listBusModelView, this)

        recyclerBusList.recycleView.adapter = adapter

        recyclerBusList.setState(State.FILLED)
    }

    override fun onClick(idBus: Int) {
        presenter.onBusClicked(idBus)
    }
}