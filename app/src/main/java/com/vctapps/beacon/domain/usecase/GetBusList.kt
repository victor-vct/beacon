package com.vctapps.beacon.domain.usecase

import com.vctapps.beacon.core.domain.UseCase
import io.reactivex.Completable

interface GetBusList : UseCase<Completable>{

    fun setIdBusStop(idBusStop: Int): GetBusList

}