package com.vctapps.beacon.presentation.listbus.presenter

import com.vctapps.beacon.core.presentation.BasePresenter
import com.vctapps.beacon.presentation.model.BusModelView

interface ListBusPresenter: BasePresenter{

    fun onBusClicked(position: Int)

}