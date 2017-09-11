package com.vctapps.beacon.presentation.requestbus

import android.content.Intent
import com.vctapps.beacon.core.presentation.BasePresenter

interface RequestBusPresenter: BasePresenter {

    fun processIntent(intent: Intent?)

    fun onClickedFavoriteButton()

    fun onClickedCancelButton()

}