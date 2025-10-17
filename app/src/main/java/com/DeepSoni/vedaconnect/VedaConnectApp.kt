// In com/DeepSoni/vedaconnect/VedaConnectApp.kt

package com.DeepSoni.vedaconnect

import android.app.Application
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository

class VedaConnectApp : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize the repository with the application context
        MandalaOneSuktasRepository.initialize(this)
    }
}
    