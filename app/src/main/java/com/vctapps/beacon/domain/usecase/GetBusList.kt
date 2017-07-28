package com.vctapps.beacon.domain.usecase

import com.vctapps.beacon.core.domain.UseCase
import com.vctapps.beacon.domain.entity.Bus
import io.reactivex.Maybe

interface GetBusList : UseCase<Maybe<List<Bus>>>{

    fun setIdBusStop(idBusStop: Int): GetBusList

}