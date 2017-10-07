package com.vctapps.beacon.core.presentation

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.Intent
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.presentation.detailbus.view.DetailBusViewImpl
import com.vctapps.beacon.presentation.listbus.view.ListBusViewImpl
import com.vctapps.beacon.presentation.requestbus.RequestBusViewImpl
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopViewImpl

class RouterImpl: Router {

    private lateinit var context: Context

    override fun setActivityContext(context: Context) {
        this.context = context
    }

    override fun goToSearchBusStop() {
        val intent = Intent(context, SearchBusStopViewImpl::class.java)
        context.startActivity(intent)
    }

    override fun goToDetailBus(bus: Bus) {
        val intent = Intent(context, DetailBusViewImpl::class.java)

        intent.putExtra(DetailBusViewImpl.BUS_MODEL, bus)

        context.startActivity(intent)
    }

    override fun goToRequestedBus(bus: Bus) {
        val intent = Intent(context, RequestBusViewImpl::class.java)

        intent.putExtra(RequestBusViewImpl.BUS_MODEL, bus)

        context.startActivity(intent)
    }

    override fun goToListBus() {
        val intent = Intent(context, ListBusViewImpl::class.java)
        context.startActivity(intent)
    }

    override fun goToEnableBluetooth(identifier: Int) {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)

        val activity = context as BaseActivity

        activity.startActivityForResult(enableBtIntent, identifier)
    }
}