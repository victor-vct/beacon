package com.vctapps.beacon.data.bus.datasource.local

import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Completable
import io.reactivex.Maybe

interface BusLocalDatasource {

    fun saveFavorite(bus: Bus): Completable

    fun deleteFavorite(bus: Bus): Completable

    fun getFavoritesIds(): Maybe<MutableList<Long>>
}