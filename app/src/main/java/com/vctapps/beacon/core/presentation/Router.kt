package com.vctapps.beacon.core.presentation

import android.content.Context
import com.vctapps.beacon.domain.entity.Bus

interface Router {

    fun setActivityContext(context: Context)

    fun goToSearchBusStop()

    fun goToDetailBus(bus: Bus)

    fun goToRequestedBus(bus: Bus)

    fun goToListBus()

    fun goToEnableBluetooth(identifier: Int)

}