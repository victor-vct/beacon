package com.vctapps.beacon.data.bus.datasource.remote

import com.vctapps.beacon.core.data.ApiService
import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRequireResponse
import io.reactivex.Completable
import io.reactivex.Maybe

class BusRemoteDatasourceImpl(private val apiService: ApiService): BusRemoteDatasource {

    override fun requestBusList(busStopId: Int) = apiService.getBusList(busStopId)

    override fun requestBus(busModuleId: String, busStopId: Int) =
            apiService.requestBus(busModuleId, busStopId)

    override fun cancelRequestBus(requestId: String) = apiService.cancelRequire(requestId)

}