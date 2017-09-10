package com.vctapps.beacon.presentation.detailbus.view

import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.presentation.model.BusModelView

interface DetailBusView: BaseView{

    fun loadInfos(bus: BusModelView)

    fun showMessageError()

    fun goToRequestBus()

}