package com.vctapps.beacon.data.bus.datasource.remote

import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import io.reactivex.Completable
import io.reactivex.Maybe

interface BusRemoteDatasource {

    fun requestBusList(busStopId: Int): Maybe<BusRemoteEntity>

    fun requestBus(busId: Int, BusStopId: Int): Completable

}