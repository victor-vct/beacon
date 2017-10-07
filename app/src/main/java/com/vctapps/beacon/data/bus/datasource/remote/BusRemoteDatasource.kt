package com.vctapps.beacon.data.bus.datasource.remote

import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRequireResponse
import io.reactivex.Completable
import io.reactivex.Maybe

interface BusRemoteDatasource {

    fun requestBusList(busStopId: Int): Maybe<BusRemoteEntity>

    fun requestBus(busModuleId: String, busStopId: Int): Maybe<BusRequireResponse>

    fun cancelRequestBus(requestId: String): Completable

}