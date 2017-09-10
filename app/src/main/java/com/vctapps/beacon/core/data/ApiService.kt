package com.vctapps.beacon.core.data

import com.vctapps.beacon.data.bus.datasource.remote.entity.BusRemoteEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("busstop/{id_busstop}/bus/")
    fun getBusList(@Path("id_busstop") busStopId: Int): Maybe<BusRemoteEntity>

    @GET("bus/{id_bus}/require/{id_busstop}")
    fun requestBus(@Path("id_bus") busId: Int, @Path("id_busstop") busStopId: Int): Completable

}