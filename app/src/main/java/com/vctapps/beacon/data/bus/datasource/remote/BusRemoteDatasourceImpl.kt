package com.vctapps.beacon.data.bus.datasource.remote

import com.vctapps.beacon.core.data.ApiService
import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import io.reactivex.Completable
import io.reactivex.Maybe

class BusRemoteDatasourceImpl(val apiService: ApiService): BusRemoteDatasource {

    override fun requestBusList(busStopId: Int): Maybe<BusRemoteEntity> {
        return apiService.getBusList(busStopId)
    }

    override fun requestBus(busModuleId: String, busStopId: Int): Completable {
        return apiService.requestBus(busModuleId, busStopId)
    }
}