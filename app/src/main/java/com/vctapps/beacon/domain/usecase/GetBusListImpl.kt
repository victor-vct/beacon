package com.vctapps.beacon.domain.usecase

import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Maybe

class GetBusListImpl(val busStopRepository: BusStopRepository,
                     val busRepository: BusRepository): GetBusList {

    override fun run(): Maybe<MutableList<Bus>> {
        return busStopRepository.getCloseBusStop()
                .flatMap(this::getBusList)
    }

    fun getBusList(busStopId: String): Maybe<MutableList<Bus>>{
        return busRepository.getBusList(busStopId.toInt())
    }
}