package com.vctapps.beacon.service.voice

import io.reactivex.Completable

interface Talk {

    fun speak(text: String)

    fun init(): Completable

    fun close()

}