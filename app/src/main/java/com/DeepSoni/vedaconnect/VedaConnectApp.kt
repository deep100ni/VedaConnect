package com.DeepSoni.vedaconnect

import android.app.Application
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository

class VedaConnectApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MandalaOneSuktasRepository.initialize(this)
    }
}
    