package com.vctapps.beacon.core.presentation

import android.content.Context

interface BaseView {

    fun showLoading()

    fun showMessageError()

    fun hideLoading()

    fun getContext(): Context

    fun close()

}