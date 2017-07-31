package com.vctapps.beacon.presentation.listbus.presenter

import com.vctapps.beacon.core.presentation.BasePresenter

interface ListBusPresenter: BasePresenter{

    fun onBusClicked(busId: Int)

}