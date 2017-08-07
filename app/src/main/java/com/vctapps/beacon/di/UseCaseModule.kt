package com.vctapps.beacon.di

import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.domain.usecase.GetBusList
import com.vctapps.beacon.domain.usecase.GetBusListImpl
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun providesGetBusList(busStopRepository: BusStopRepository,
                           busRepository: BusRepository): GetBusList {
        return GetBusListImpl(busStopRepository, busRepository)
    }

}