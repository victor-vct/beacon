package com.vctapps.beacon.di

import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.domain.usecase.GetBusList
import com.vctapps.beacon.domain.usecase.RequestBus
import com.vctapps.beacon.presentation.detailbus.presenter.DetailBusPresenter
import com.vctapps.beacon.presentation.detailbus.presenter.DetailBusPresenterImpl
import com.vctapps.beacon.presentation.listbus.presenter.ListBusPresenter
import com.vctapps.beacon.presentation.listbus.presenter.ListBusPresenterImpl
import com.vctapps.beacon.presentation.requestbus.RequestBusPresenter
import com.vctapps.beacon.presentation.requestbus.RequestBusPresenterImpl
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopPresenter
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopPresenterImpl
import com.vctapps.beacon.service.voice.Talk
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun providesSearchBusStopPresenter(busStopRepository: BusStopRepository,
                                       talk: Talk):SearchBusStopPresenter {
        return SearchBusStopPresenterImpl(busStopRepository, talk)
    }

    @Provides
    fun providesListBusPresenter(getBusList: GetBusList,
                                 talk: Talk): ListBusPresenter {
        return ListBusPresenterImpl(getBusList, talk)
    }

    @Provides
    fun providesDetailBusPresenter(talk: Talk, requestBus: RequestBus): DetailBusPresenter {
        return DetailBusPresenterImpl(talk, requestBus)
    }

    @Provides
    fun providesRequestBusPresenter(busRepository: BusRepository,
                                    talk: Talk): RequestBusPresenter {
        return RequestBusPresenterImpl(talk, busRepository)
    }

}