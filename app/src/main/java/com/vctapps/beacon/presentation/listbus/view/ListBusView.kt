package com.vctapps.beacon.presentation.listbus.view

import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.presentation.listbus.model.BusModelView

interface ListBusView: BaseView{

    fun loadList(listBusModelView: MutableList<BusModelView>)

}