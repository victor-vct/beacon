package com.vctapps.beacon.presentation.requestbus

import android.content.Intent
import com.vctapps.beacon.R
import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.core.presentation.Router
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class RequestBusPresenterImpl(private val talk: Talk,
                              private val busRepository: BusRepository,
                              private val router: Router): RequestBusPresenter {

    lateinit var baseView: RequestBusView

    val disposable = CompositeDisposable()

    lateinit var bus: Bus

    override fun attachTo(view: BaseView) {
        if(view is RequestBusView) {
            this.baseView = view
            router.setActivityContext(baseView.getContext())
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

        busRepository.cancelRequestBus()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    router.goToSearchBusStop()
                    baseView.close()
                },{
                    error -> Timber.e(error)
                    baseView.hideLoading()
                })
    }

    override fun processIntent(intent: Intent?) {
        if(intent != null && intent.hasExtra(RequestBusViewImpl.BUS_MODEL)){
            bus = intent.extras.get(RequestBusViewImpl.BUS_MODEL) as Bus

            baseView.hideLoading()

            talk.speak(baseView.getContext().getString(R.string.success_message_request_bus))
        }else{
            //baseView.showMessageError()
        }
    }
}