package com.vctapps.beacon.data.datasource.busstop.beacon

import android.content.Context
import android.os.RemoteException
import com.vctapps.beacon.data.datasource.busstop.RemoteBusstopDatasource
import io.reactivex.Completable
import io.reactivex.Maybe
import org.altbeacon.beacon.*
import timber.log.Timber

class BeaconDatasourceImplRemote(val context: Context): RemoteBusstopDatasource {

    companion object {
        val BEACON_LAYOUT = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"
        val VALID_PRE_FIX_BEACON_IDENTIFIER = "655"

        fun getInstance(context: Context): RemoteBusstopDatasource {
            return BeaconDatasourceImplRemote(context)
        }
    }

    var beaconManager = providesBeaconManager()
    lateinit var region: Region

    override fun bind(beaconConsumer: BeaconConsumer): Completable {
        return Completable.create { emmiter ->
            beaconManager.bind(beaconConsumer)

            emmiter.onComplete()
        }
    }

    override fun unbind(beaconConsumer: BeaconConsumer): Completable {
        return Completable.create { emmiter ->
            beaconManager.unbind(beaconConsumer)

            emmiter.onComplete()
        }
    }

    override fun getCloseBusStop(): Maybe<String> {
        return Maybe.create { emitter ->
            beaconManager.addRangeNotifier { beacons, _ ->
                var busStopId: String? = extractValidBusStopId(beacons)

                if(!busStopId.isNullOrEmpty()){
                    emitter.onSuccess(busStopId!!)
                    beaconManager.stopRangingBeaconsInRegion(this.region)
                }
            }

            try {
                region = Region("Beacon", null, null, null)
                beaconManager.startRangingBeaconsInRegion(region)
            } catch (e: RemoteException) {
                Timber.e(e)
                emitter.onError(e)
            }

        }
    }

    private fun extractValidBusStopId(beacons: MutableCollection<Beacon>): String?{
        var validBeacon: Beacon? = null
        beacons.iterator()
                .forEach { beacon ->
                    if(validBeacon == null) validBeacon = beacon

                    if(isValidBeaconIdentifier(validBeacon!!.id3) &&
                            validBeacon!!.distance > beacon.distance) validBeacon = beacon
                }

        return if(validBeacon != null) validBeacon!!.id3.toString() else ""
    }

    private fun isValidBeaconIdentifier(identifier: Identifier?): Boolean {
        val preFixIdentifier = identifier.toString().substring(0, VALID_PRE_FIX_BEACON_IDENTIFIER.length)

        return preFixIdentifier == VALID_PRE_FIX_BEACON_IDENTIFIER
    }

    fun providesBeaconManager(): BeaconManager {
        val beaconManager = BeaconManager.getInstanceForApplication(context)

        beaconManager.beaconParsers.add(BeaconParser()
                .setBeaconLayout(BEACON_LAYOUT))

        return beaconManager
    }

}