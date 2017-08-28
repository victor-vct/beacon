package com.vctapps.beacon.service.voice

import android.content.Context
import android.os.Build
import android.speech.tts.TextToSpeech
import io.reactivex.Completable
import io.reactivex.CompletableEmitter
import timber.log.Timber
import java.util.*

class TextToSpeechTalkImpl(val context: Context): Talk, TextToSpeech.OnInitListener {

    var textToSpeech: TextToSpeech? = null

    private lateinit var emmiter: CompletableEmitter

    override fun speak(text: String) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }else{
            textToSpeech?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
        }
    }

    override fun init(): Completable {
        return Completable.create { emmiter ->
            if(textToSpeech == null){
                this.emmiter = emmiter
                textToSpeech = TextToSpeech(context, this)
            }else{
                emmiter.onComplete()
            }
        }
    }

    override fun close() {
        textToSpeech?.shutdown()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {

            val result = textToSpeech?.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Timber.e("This Language is not supported")
                emmiter.onError(Throwable("This Language is not supported"))
            } else {
                Timber.d("Text to speech started")
                emmiter.onComplete()
            }

        } else {
            Timber.e("Text to speech initilization Failed!")
            emmiter.onError(Throwable("Text to speech initilization Failed!"))
        }
    }
}