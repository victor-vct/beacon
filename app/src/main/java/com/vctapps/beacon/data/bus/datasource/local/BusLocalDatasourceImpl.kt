package com.vctapps.beacon.data.bus.datasource.local

import android.content.Context
import android.content.SharedPreferences
import com.vctapps.beacon.core.InvalidData
import com.vctapps.beacon.data.bus.datasource.local.entity.BusLocalEntity
import com.vctapps.beacon.data.bus.datasource.local.mapper.BusLocalMapper
import com.vctapps.beacon.domain.entity.Bus
import io.objectbox.Box
import io.reactivex.Completable
import io.reactivex.Maybe

class BusLocalDatasourceImpl(val busBox: Box<BusLocalEntity>,
                             val context: Context): BusLocalDatasource {

    private val BUS_REQUIRE = "BUS_REQUIRE"

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

    override fun getFavoriteLines(): Maybe<MutableList<String>> {
        return Maybe.create { emitter ->
            var favoritesBus = busBox.all

            var favoriteLines = mutableListOf<String>()

            favoritesBus.forEach { (id) -> favoriteLines.add(id) }

            if(favoritesBus.isEmpty()){
                emitter.onComplete()
            }else{
                emitter.onSuccess(favoriteLines)
            }
        }
    }

    override fun saveBusRequire(uid: String): Completable {
        return Completable.create { emiter ->
            var sharedPreferences = getShared()

            sharedPreferences.edit().putString(BUS_REQUIRE, uid).commit()

            emiter.onComplete()
        }
    }

    override fun deleteBusRequire(): Completable {
        return Completable.create { emiter ->
            var sharedPreferences = getShared()

            sharedPreferences.edit().clear().commit()

            emiter.onComplete()
        }
    }

    override fun getBusRequire(): Maybe<String> {
        return Maybe.create { emiter ->
            val sharedPreferences = getShared()
            val uid = sharedPreferences.getString(BUS_REQUIRE, InvalidData.STRING)

            if(uid == InvalidData.STRING){
                emiter.onComplete()
            } else {
                emiter.onSuccess(uid)
            }
        }
    }

    private fun getShared() = context.getSharedPreferences(
            "com.vctapps.BUS",
            Context.MODE_PRIVATE
    )
}