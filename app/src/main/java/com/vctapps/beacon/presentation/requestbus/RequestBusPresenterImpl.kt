package com.vctapps.beacon.presentation.requestbus

import android.content.Intent
import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RequestBusPresenterImpl(val talk: Talk,
                              val busRepository: BusRepository): RequestBusPresenter {

    lateinit var baseView: RequestBusViewImpl

    val disposable = CompositeDisposable()

    lateinit var bus: Bus

    override fun attachTo(view: BaseView) {
        if(view is RequestBusViewImpl) {
            this.baseView = view
        }
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun onClickedFavoriteButton() {
        baseView.showLoading()

        disposable.add(
                busRepository.saveFavoriteBus(bus)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnError { /*baseView.showMessageError()*/ }
                        .subscribe { baseView.hideLoading() }
        )
    }

    override fun onClickedCancelButton() {
        baseView.showLoading()
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun processIntent(intent: Intent?) {
        if(intent != null && intent.hasExtra(RequestBusViewImpl.BUS_MODEL)){
            bus = intent.extras.get(RequestBusViewImpl.BUS_MODEL) as Bus

            baseView.hideLoading()

            talk.speak(baseView.getString(R.string.success_message_request_bus))
        }else{
            //baseView.showMessageError()
        }
    }
}