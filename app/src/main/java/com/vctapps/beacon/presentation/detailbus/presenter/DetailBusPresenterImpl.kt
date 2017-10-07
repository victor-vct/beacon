package com.vctapps.beacon.presentation.detailbus.presenter

import android.content.Intent
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.core.presentation.Router
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.domain.usecase.RequestBus
import com.vctapps.beacon.presentation.detailbus.view.DetailBusView
import com.vctapps.beacon.presentation.detailbus.view.DetailBusViewImpl
import com.vctapps.beacon.presentation.model.BusModelView
import com.vctapps.beacon.presentation.model.mapper.BusModelViewMapper
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class DetailBusPresenterImpl(private val talk: Talk,
                             private val requestBus: RequestBus,
                             private val router: Router): DetailBusPresenter{

    val disposable = CompositeDisposable()

    lateinit var detailBusView: DetailBusView

    lateinit var busModelView: BusModelView

    lateinit var bus: Bus

    override fun attachTo(view: BaseView) {
        if(view is DetailBusView){
            detailBusView = view

            router.setActivityContext(detailBusView.getContext())
        }else{
            throw IllegalArgumentException("Needs to be a DetailBusView type")
        }

        detailBusView.showLoading()
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun processIntent(intent: Intent?) {
        if(intent != null && intent.hasExtra(DetailBusViewImpl.BUS_MODEL)){
            bus = intent.extras.get(DetailBusViewImpl.BUS_MODEL) as Bus

            busModelView = BusModelViewMapper.transformFrom(bus)

            detailBusView.loadInfos(busModelView)

            detailBusView.hideLoading()

            talkAboutBusDetail(busModelView)
        }else{
            detailBusView.showMessageError()
        }
    }

    override fun onRequestBusClicked() {
        disposable.add(requestBus.setId(busModelView.id)
                .run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { detailBusView.showLoading() }
                .doOnComplete({
                    router.goToRequestedBus(bus)
                    detailBusView.close()
                })
                .subscribe())
    }

    private fun talkAboutBusDetail(busModelView: BusModelView){
        var text: String

        text = busModelView.name + " chega em " + " 3 minutos"

        talk.speak(text)
    }

    override fun onBackPressed() {
        disposable.clear()
    }
}