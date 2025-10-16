package com.DeepSoni.vedaconnect

import android.app.Application
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository

class VedaConnectApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize your repository here
        // This ensures the data is loaded once when the app starts
        MandalaOneSuktasRepository.initialize(this)
    }
}