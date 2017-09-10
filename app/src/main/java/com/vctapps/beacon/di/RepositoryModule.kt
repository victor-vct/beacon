package com.vctapps.beacon.di

import android.content.Context
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.vctapps.beacon.core.data.ApiService
import com.vctapps.beacon.data.bus.BusRepository
import com.vctapps.beacon.data.bus.BusRepositoryImpl
import com.vctapps.beacon.data.bus.datasource.remote.BusRemoteDatasource
import com.vctapps.beacon.data.bus.datasource.remote.BusRemoteDatasourceImpl
import com.vctapps.beacon.data.busstop.BusStopRepository
import com.vctapps.beacon.data.busstop.BusStopRepositoryImpl
import com.vctapps.beacon.data.busstop.datasource.LocalBusStopDatasource
import com.vctapps.beacon.data.busstop.datasource.RemoteBusstopDatasource
import com.vctapps.beacon.data.busstop.datasource.beacon.BeaconDatasourceImplRemote
import com.vctapps.beacon.data.busstop.datasource.cache.CacheBusStopDatasourceImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@Singleton
class RepositoryModule {

    val BASE_URL = "http://192.168.25.21:3000"

    @Provides
    fun providesBusStopRepository(remoteBusstopDatasource: RemoteBusstopDatasource,
                                  localBusStopDatasource: LocalBusStopDatasource): BusStopRepository {
        return BusStopRepositoryImpl(remoteBusstopDatasource, localBusStopDatasource)
    }

    @Provides
    fun providesBusRepository(busRemoteDatasource: BusRemoteDatasource): BusRepository {
        return BusRepositoryImpl(busRemoteDatasource)
    }

    @Provides
    fun providesBusRemoteDataSource(apiService: ApiService): BusRemoteDatasource {
        return BusRemoteDatasourceImpl(apiService)
    }

    @Provides
    fun providesBeaconDatasource(context: Context): RemoteBusstopDatasource {
        return BeaconDatasourceImplRemote(context)
    }

    @Provides
    fun providesCacheDatasource(): LocalBusStopDatasource {
        return CacheBusStopDatasourceImpl()
    }

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService{
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .client(okHttpClient)
                .build()
    }

    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return logging
    }

    @Provides
    fun providesOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient{
        val okhttp = OkHttpClient.Builder()
        okhttp.addInterceptor(loggingInterceptor)

        return okhttp.build()
    }

}