package com.vctapps.beacon.presentation.searchbusstop

import android.bluetooth.BluetoothAdapter
import android.content.Intent
import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.core.throwable.BluetoothIsNotEnabledError
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.presentation.listbus.view.ListBusViewImpl
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class SearchBusStopPresenterImpl(val busStopRepository: BusStopRepository,
                                 val talk: Talk) : SearchBusStopPresenter {

    lateinit var searchBusStopView: SearchBusStopViewImpl

    var disposable = CompositeDisposable()

    override fun attachTo(view: BaseView) {
        if (view is SearchBusStopViewImpl) {
            searchBusStopView = view
        } else {
            Timber.e(IllegalArgumentException("View is not a SearchBusStopView"))
        }

        searchBusStopView.showLoading()

        talk.init()
                .subscribe {
                    initServices()
                }
    }

    private fun initServices() {
        disposable.add(busStopRepository
                .setUp()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("Setup completed")
                    onBoundService()
                }, { error ->
                    Timber.e("Setup not completed: " + error)

                    if (error is BluetoothIsNotEnabledError) {
                        searchBusStopView.showBluetoothNotEnable()
                        talk.speak(searchBusStopView.getString(R.string.enable_bluetooth_dialog_message))
                    }
                })
        )
    }

    override fun dettachFrom() {
        disposable.clear()
    }

    override fun onBoundService() {
        disposable.add(busStopRepository
                .getCloseBusStop()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ busStopId ->
                    Timber.d("BusStopId found: " + busStopId)
                    busStopRepository.close()
                            .subscribe {
                                searchBusStopView.hideLoading()
                                goToListBus()
                            }
                }, { error -> Timber.e("Error on found beacon. " + error) }))
    }

    override fun onClickedToEnableBluetooth() {
        val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
        searchBusStopView.startActivityForResult(enableBtIntent, 6)
    }

    private fun goToListBus() {
        var intent = Intent(searchBusStopView.applicationContext, ListBusViewImpl::class.java)
        searchBusStopView.startActivity(intent)
    }

}