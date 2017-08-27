package com.vctapps.beacon.data.busstop.datasource

import io.reactivex.Completable
import io.reactivex.Maybe
import org.altbeacon.beacon.BeaconConsumer

interface RemoteBusstopDatasource {

    fun bind(): Completable

    fun unbind(): Completable

    fun getCloseBusStop(): Maybe<String>

}