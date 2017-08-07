package com.vctapps.beacon.di

import android.content.Context
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.bus.BusRepositoryImpl
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.data.busstop.BusStopRepositoryImpl
import com.vctapps.beacon.data.busstop.datasource.LocalBusStopDatasource
import com.vctapps.beacon.data.busstop.datasource.RemoteBusstopDatasource
import com.vctapps.beacon.data.busstop.datasource.beacon.BeaconDatasourceImplRemote
import com.vctapps.beacon.data.busstop.datasource.cache.CacheBusStopDatasourceImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun providesBusStopRepository(remoteBusstopDatasource: RemoteBusstopDatasource,
                                  localBusStopDatasource: LocalBusStopDatasource): BusStopRepository {
        return BusStopRepositoryImpl(remoteBusstopDatasource, localBusStopDatasource)
    }

    @Provides
    fun providesBusRepository(): BusRepository {
        return BusRepositoryImpl()
    }

    @Provides
    fun providesBeaconDatasource(context: Context): RemoteBusstopDatasource {
        return BeaconDatasourceImplRemote(context)
    }

    @Provides
    fun providesCacheDatasource(): LocalBusStopDatasource {
        return CacheBusStopDatasourceImpl()
    }
}