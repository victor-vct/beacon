package com.vctapps.beacon.presentation.listbus.presenter

import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.bus.BusRepositoryImpl
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.data.busstop.BusStopRepositoryImpl
import com.vctapps.beacon.domain.usecase.GetBusList
import com.vctapps.beacon.domain.usecase.GetBusListImpl
import com.vctapps.beacon.presentation.listbus.model.mapper.BusModelViewMapper
import com.vctapps.beacon.presentation.listbus.view.ListBusViewImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListBusPresenterImpl : ListBusPresenter {

    lateinit var getBuslist: GetBusList
    lateinit var listBusView: ListBusViewImpl

    val disposable = CompositeDisposable()

    override fun attachTo(view: BaseView) {
        if(view is ListBusViewImpl){
            listBusView = view
        }else{
            throw Throwable("View needs to be ListBusView")
        }

        listBusView.showLoading()

        getBuslist = GetBusListImpl(providesBusListRepository(), providesBusRepository())

        disposable.add(getBuslist.run()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(BusModelViewMapper::transformFrom)
                .subscribe(listBusView::loadList,
                        { error -> Timber.e(error)}))
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun onBusClicked(busId: Int) {
        listBusView.showLoading()
    }

    private fun providesBusRepository(): BusRepository {
        return BusRepositoryImpl()
    }

    private fun providesBusListRepository(): BusStopRepository {
        return BusStopRepositoryImpl(listBusView.applicationContext)
    }
}