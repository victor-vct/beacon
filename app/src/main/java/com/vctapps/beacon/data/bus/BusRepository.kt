package com.vctapps.beacon.data.bus

import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Completable
import io.reactivex.Maybe

interface BusRepository {

    fun saveFavoriteBus(bus: Bus): Completable

    fun getBusList(busStopId: Int): Maybe<MutableList<Bus>>

    fun getBus(busStopId: Int, busId: Int): Maybe<Bus>

    fun requestBus(busStopId: Int, busModuleId: String): Completable

}