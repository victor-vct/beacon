package com.vctapps.beacon.presentation.requestbus

import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.service.voice.Talk

class RequestBusPresenterImpl(val talk: Talk): RequestBusPresenter {

    lateinit var baseView: RequestBusViewImpl

    override fun attachTo(view: BaseView) {
        if(view is RequestBusViewImpl) {
            this.baseView = view
        }

        baseView.hideLoading()

        talk.speak(baseView.getString(R.string.success_message_request_bus))
    }

    override fun dettachFrom() {

    }

    override fun onClickedFavoriteButton() {
        baseView.showLoading()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onClickedCancelButton() {
        baseView.showLoading()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}