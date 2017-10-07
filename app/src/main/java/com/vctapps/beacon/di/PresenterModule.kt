package com.vctapps.beacon.di

import android.content.Context
import com.vctapps.beacon.core.presentation.Router
import com.vctapps.beacon.core.presentation.RouterImpl
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
    fun providesSearchBusStopPresenter(busRepository: BusRepository,
                                       busStopRepository: BusStopRepository,
                                       talk: Talk,
                                       router: Router):SearchBusStopPresenter {
        return SearchBusStopPresenterImpl(busRepository, busStopRepository, talk, router)
    }

    @Provides
    fun providesListBusPresenter(getBusList: GetBusList,
                                 talk: Talk,
                                 router: Router): ListBusPresenter {
        return ListBusPresenterImpl(getBusList, talk, router)
    }

    @Provides
    fun providesDetailBusPresenter(talk: Talk, requestBus: RequestBus,
                                   router: Router): DetailBusPresenter {
        return DetailBusPresenterImpl(talk, requestBus, router)
    }

    @Provides
    fun providesRequestBusPresenter(busRepository: BusRepository,
                                    talk: Talk,
                                    router: Router): RequestBusPresenter {
        return RequestBusPresenterImpl(talk, busRepository, router)
    }

    @Provides
    fun providesRouter(): Router {
        return RouterImpl()
    }

}