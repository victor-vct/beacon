package com.vctapps.beacon.di

import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.domain.usecase.GetBusList
import com.vctapps.beacon.presentation.listbus.presenter.ListBusPresenter
import com.vctapps.beacon.presentation.listbus.presenter.ListBusPresenterImpl
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopPresenter
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopPresenterImpl
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    fun providesSearchBusStopPresenter(busStopRepository: BusStopRepository):SearchBusStopPresenter {
        return SearchBusStopPresenterImpl(busStopRepository)
    }

    @Provides
    fun providesListBusPresenter(getBusList: GetBusList): ListBusPresenter {
        return ListBusPresenterImpl(getBusList)
    }

}