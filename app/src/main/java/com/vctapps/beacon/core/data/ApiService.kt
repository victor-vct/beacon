package com.vctapps.beacon.core.data

import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRequireResponse
import io.reactivex.Completable
import io.reactivex.Maybe
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("busstop/{id_busstop}/bus/")
    fun getBusList(@Path("id_busstop") busStopId: Int): Maybe<MutableList<BusRemoteEntity>>

    @POST("busmodule/{id_busmodule}/busstop/{id_busstop}")
    fun requestBus(@Path("id_busmodule") busModuleId: String, @Path("id_busstop") busStopId: Int): Maybe<BusRequireResponse>

    @DELETE("userRequire/{id_user_require}")
    fun cancelRequire(@Path("id_user_require") idRequire: String): Completable
}