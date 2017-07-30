package com.vctapps.beacon.presentation

import com.vctapps.beacon.core.presentation.BaseActivity
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.data.Repository
import com.vctapps.beacon.data.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchBusStopPresenterImpl : SearchBusStopPresenter {

    lateinit var repository: Repository
    lateinit var searchBusStopView: SearchBusStopView
    var disposable = CompositeDisposable()

    override fun attachTo(view: BaseView) {
        if(view is SearchBusStopView){
            searchBusStopView = view
        }else{
            Timber.e(IllegalArgumentException("View is not a SearchBusStopView"))
        }

        searchBusStopView.showLoading()

        repository = RepositoryImpl(view as BaseActivity)

        disposable.add(repository
                .setUp(searchBusStopView)
                .subscribeOn(Schedulers.io())
                .subscribe({Timber.d("Setup completed")},
                        {error -> Timber.e("Setup not completed: " + error)}))
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun onBoundService() {
        disposable.add(repository
                .getCloseBusStop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    busStopId -> Timber.d("BusStopId found: " + busStopId)
                    repository.close(searchBusStopView)
                            .subscribe {
                                searchBusStopView.hideLoading()}
                            }, {
                    error -> Timber.e("Error on found beacon. " + error)}))
    }

}