package com.vctapps.beacon.core.presentation

interface BasePresenter {

    fun attachTo(view: BaseView)

    fun dettachFrom()

}