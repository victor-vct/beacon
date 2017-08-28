package com.vctapps.beacon.di

import android.content.Context
import com.vctapps.beacon.di.scope.BeaconScope
import com.vctapps.beacon.service.voice.Talk
import com.vctapps.beacon.service.voice.TextToSpeechTalkImpl
import dagger.Module
import dagger.Provides

@Module
class VoiceModule {

    @Provides
    @BeaconScope
    fun providesTalk(context: Context): Talk {
        return TextToSpeechTalkImpl(context)
    }

}
