package com.vctapps.beacon.presentation.detailbus.presenter

import android.content.Intent
import com.vctapps.beacon.core.presentation.BasePresenter

interface DetailBusPresenter: BasePresenter {

    fun processIntent(intent: Intent?)

    fun onRequestBusClicked()

    fun onBackPressed()

}