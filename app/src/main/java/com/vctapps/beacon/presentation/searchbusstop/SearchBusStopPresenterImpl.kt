package com.vctapps.beacon.presentation.searchbusstop

import android.content.Intent
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.presentation.listbus.view.ListBusViewImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchBusStopPresenterImpl(val busStopRepository: BusStopRepository) : SearchBusStopPresenter {

    lateinit var searchBusStopView: SearchBusStopViewImpl

    var disposable = CompositeDisposable()

    override fun attachTo(view: BaseView) {
        if(view is SearchBusStopViewImpl){
            searchBusStopView = view
        }else{
            Timber.e(IllegalArgumentException("View is not a SearchBusStopView"))
        }

        searchBusStopView.showLoading()

        disposable.add(busStopRepository
                .setUp(searchBusStopView)
                .subscribeOn(Schedulers.io())
                .subscribe({Timber.d("Setup completed")},
                        {error -> Timber.e("Setup not completed: " + error)}))
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun onBoundService() {
        disposable.add(busStopRepository
                .getCloseBusStop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    busStopId -> Timber.d("BusStopId found: " + busStopId)
                    busStopRepository.close(searchBusStopView)
                            .subscribe {
                                searchBusStopView.hideLoading()
                                goToListBus()
                            }
                    }, {
                    error -> Timber.e("Error on found beacon. " + error)}))
    }

    private fun goToListBus(){
        var intent = Intent(searchBusStopView.applicationContext, ListBusViewImpl::class.java)
        searchBusStopView.startActivity(intent)
    }

}