package com.vctapps.beacon.domain.usecase

import com.vctapps.beacon.core.domain.UseCase
import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Maybe

interface GetBusDetails: UseCase<Maybe<Bus>>{

    fun setIdBus(idBus: Int): RequestBus

    fun setIdBusStop(idBusStop: Int): RequestBus

}