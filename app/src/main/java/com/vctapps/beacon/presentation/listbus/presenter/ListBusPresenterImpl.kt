package com.vctapps.beacon.presentation.listbus.presenter

import android.content.Intent
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.domain.usecase.GetBusList
import com.vctapps.beacon.presentation.detailbus.view.DetailBusViewImpl
import com.vctapps.beacon.presentation.model.mapper.BusModelViewMapper
import com.vctapps.beacon.presentation.listbus.view.ListBusViewImpl
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListBusPresenterImpl(val getBuslist: GetBusList,
                           val talk: Talk) : ListBusPresenter {

    lateinit var listBusView: ListBusViewImpl

    lateinit var busList: MutableList<Bus>

    val disposable = CompositeDisposable()

    private var alreadySpeaked = false

    override fun attachTo(view: BaseView) {
        if(view is ListBusViewImpl){
            listBusView = view
        }else{
            throw Throwable("View needs to be ListBusView")
        }

        listBusView.showLoading()

        talk.init()
                .subscribe {
                    getListBus()
                }
    }

    private fun getListBus() {
        disposable.add(getBuslist.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ busList ->
                    this.busList = busList
                    talkNumberOfBusFound(busList.size.toString())
                    listBusView.loadList(BusModelViewMapper.transformFrom(busList))
                },
                        { error -> Timber.e(error) }))
    }

    private fun talkNumberOfBusFound(numberOfBus: String) {
        if(alreadySpeaked) return

        talk.speak(numberOfBus + " ônibus encontrados")
        alreadySpeaked = true
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun onBusClicked(position: Int) {
        goToDetailBus(busList.get(position))
    }

    private fun goToDetailBus(bus: Bus){
        var intent = Intent(listBusView.applicationContext, DetailBusViewImpl::class.java)

        intent.putExtra(DetailBusViewImpl.BUS_MODEL, bus)

        listBusView.startActivity(intent)
    }
}