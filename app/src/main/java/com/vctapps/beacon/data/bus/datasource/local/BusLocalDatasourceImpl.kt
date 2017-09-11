package com.vctapps.beacon.data.bus.datasource.local

import com.vctapps.beacon.data.bus.datasource.local.entity.BusLocalEntity
import com.vctapps.beacon.data.bus.datasource.local.mapper.BusLocalMapper
import com.vctapps.beacon.domain.entity.Bus
import io.objectbox.Box
import io.reactivex.Completable
import io.reactivex.Maybe

class BusLocalDatasourceImpl(val busBox: Box<BusLocalEntity>): BusLocalDatasource {

    override fun saveFavorite(bus: Bus): Completable {
        return Completable.create { emitter ->
            var id = busBox.put(BusLocalMapper.transformFrom(bus))

            if(id > 0){
                emitter.onComplete()
            }else{
                emitter.onError(Throwable("Can't save bus: " + bus.id))
            }
        }
    }

    override fun deleteFavorite(bus: Bus): Completable {
        return Completable.create { emitter ->
            busBox.remove(bus.id.toLong())

            emitter.onComplete()
        }
    }

    override fun getFavoritesIds(): Maybe<MutableList<Long>> {
        return Maybe.create { emitter ->
            var favoritesBus = busBox.all

            var favoritesIds = mutableListOf<Long>()

            favoritesBus.forEach { (id) -> favoritesIds.add(id) }

            if(favoritesBus.isEmpty()){
                emitter.onComplete()
            }else{
                emitter.onSuccess(favoritesIds)
            }
        }
    }
}