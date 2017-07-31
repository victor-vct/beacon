package com.vctapps.beacon.presentation.detailbus.presenter

import android.content.Intent
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.domain.usecase.RequestBus
import com.vctapps.beacon.presentation.detailbus.view.DetailBusView
import com.vctapps.beacon.presentation.detailbus.view.DetailBusViewImpl
import com.vctapps.beacon.presentation.model.BusModelView
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class DetailBusPresenterImpl: DetailBusPresenter{

    lateinit var requestBus: RequestBus

    val disposable = CompositeDisposable()

    lateinit var detailBusView: DetailBusView

    lateinit var busModelView: BusModelView

    override fun attachTo(view: BaseView) {
        if(view is DetailBusView){
            detailBusView = view
        }else{
            throw IllegalArgumentException("Needs to be a DetailBusView type")
        }

        detailBusView.showLoading()

        //TODO init use case here
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun processIntent(intent: Intent?) {
        if(intent != null && intent.hasExtra(DetailBusViewImpl.BUS_VIEW_MODEL)){
            busModelView = intent.extras.get(DetailBusViewImpl.BUS_VIEW_MODEL) as BusModelView
            detailBusView.loadInfos(busModelView)
            detailBusView.hideLoading()
        }else{
            detailBusView.showMessageError()
        }
    }

    override fun onRequestBusClicked() {
        disposable.add(requestBus.setIdBus(busModelView.id)
                .run()
                .subscribe { Timber.d("Request a bus with success") })
    }

    override fun onBackPressed() {
        disposable.clear()
    }
}