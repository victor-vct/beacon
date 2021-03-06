package com.vctapps.beacon.domain.usecase

import com.vctapps.beacon.core.domain.UseCase
import io.reactivex.Completable

interface RequestBus: UseCase<Completable> {

    fun setIdBus(idBus: Int): RequestBus

    fun setIdBusStop(idBusStop: Int): RequestBus

}