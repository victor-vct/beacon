package com.vctapps.beacon.di

import android.app.Application
import android.content.Context
import com.vctapps.beacon.di.scope.BeaconScope
import dagger.Module
import dagger.Provides

@Module
@BeaconScope
class BeaconModule(val application: Application) {

    @Provides
    @BeaconScope
    fun providesApplicationContext(): Context = application

}