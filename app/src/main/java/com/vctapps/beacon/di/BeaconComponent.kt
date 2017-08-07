package com.vctapps.beacon.di

import com.vctapps.beacon.di.scope.BeaconScope
import com.vctapps.beacon.presentation.detailbus.view.DetailBusViewImpl
import com.vctapps.beacon.presentation.listbus.view.ListBusView
import com.vctapps.beacon.presentation.listbus.view.ListBusViewImpl
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopView
import com.vctapps.beacon.presentation.searchbusstop.SearchBusStopViewImpl
import dagger.Component

@BeaconScope
@Component(modules = arrayOf(
        BeaconModule::class,
        RepositoryModule::class,
        PresenterModule::class,
        UseCaseModule::class))
interface BeaconComponent {

    fun inject(searchView: SearchBusStopViewImpl)

    fun inject(listBusView: ListBusViewImpl)

    fun inject(detailBusView: DetailBusViewImpl)

}