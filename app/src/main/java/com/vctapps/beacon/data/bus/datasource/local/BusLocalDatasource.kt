package com.vctapps.beacon.data.bus.datasource.local

import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Completable
import io.reactivex.Maybe

interface BusLocalDatasource {

    fun saveFavorite(bus: Bus): Completable

    fun deleteFavorite(bus: Bus): Completable

    fun getFavoriteLines(): Maybe<MutableList<String>>

    fun saveBusRequire(uid: String): Completable

    fun deleteBusRequire(): Completable

    fun getBusRequire(): Maybe<String>

}