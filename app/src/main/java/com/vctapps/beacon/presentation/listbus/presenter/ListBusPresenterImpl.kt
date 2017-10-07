package com.vctapps.beacon.presentation.listbus.presenter

import com.vctapps.beacon.core.presentation.BaseView
import com.vctapps.beacon.core.presentation.Router
import com.vctapps.beacon.domain.entity.Bus
import com.vctapps.beacon.domain.usecase.GetBusList
import com.vctapps.beacon.presentation.listbus.view.ListBusView
import com.vctapps.beacon.presentation.model.mapper.BusModelViewMapper
import com.vctapps.beacon.service.voice.Talk
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ListBusPresenterImpl(private val getBuslist: GetBusList,
                           private val talk: Talk,
                           private val router: Router) : ListBusPresenter {

    lateinit var listBusView: ListBusView

    lateinit var busList: MutableList<Bus>

    val disposable = CompositeDisposable()

    private var alreadySpeaked = false

    override fun attachTo(view: BaseView) {
        if(view is ListBusView){
            listBusView = view
            router.setActivityContext(listBusView.getContext())
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

                    putFavoriteInTopOf(busList)

                    this.busList = busList

                    talkNumberOfBusFound(busList.size.toString())

                    listBusView.loadList(BusModelViewMapper.transformFrom(busList))
                },
                        { error -> Timber.e(error) }))
    }

    private fun putFavoriteInTopOf(busList: MutableList<Bus>) {
        var nextPossiblePosition = 0
        var favoriteIndices = mutableListOf<Int>()
        var tempBus: Bus

        busList.forEachIndexed { index, bus -> if (bus.isFavorite) favoriteIndices.add(index) }

        favoriteIndices.forEach { index ->
            tempBus = busList[nextPossiblePosition]
            busList[nextPossiblePosition] = busList[index]
            busList[index] = tempBus
            nextPossiblePosition++
        }
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
        router.goToDetailBus(busList[position])
    }

}