package com.vctapps.beacon.presentation.searchbusstop

import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.core.presentation.Router
import com.vctapps.beacon.core.throwable.BluetoothIsNotEnabledError
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.util.*

class SearchBusStopPresenterImpl(private val busRepository: BusRepository,
                                 private val busStopRepository: BusStopRepository,
                                 private val talk: Talk,
                                 private val router: Router) : SearchBusStopPresenter {

    lateinit var searchBusStopView: SearchBusStopView

    var disposable = CompositeDisposable()

    override fun attachTo(view: BaseView) {
        if (view is SearchBusStopView) {
            searchBusStopView = view
            router.setActivityContext(searchBusStopView.getContext())
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
        disposable.add(busRepository.checkIfThereIsRequest()
                .subscribe({ uid ->
                    Timber.d("Needs open activity")
                    router.goToRequestedBus(Bus("teste", "teste", "teste", "Teste", false, ""))
                },{
                    error -> Timber.e(error)
                },{
                    busStopRepository
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
                                    talk.speak(searchBusStopView.getContext().getString(R.string.enable_bluetooth_dialog_message))
                                }
                            })
                }))
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
                                router.goToListBus()
                                searchBusStopView.close()
                            }
                }, { error -> Timber.e("Error on found beacon. " + error) }))
    }

    override fun onClickedToEnableBluetooth() {
        router.goToEnableBluetooth(6)
    }

}