package com.DeepSoni.vedaconnect

import android.app.Application

class VedaConnectApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // ✅ REMOVED: Initialization is now handled by the ViewModel,
        // which is tied to the lifecycle of the screen that needs the data.
        // This prevents loading data that might never be seen by the user.
        /*
        CoroutineScope(Dispatchers.IO).launch {
            MandalaOneSuktasRepository.initialize(applicationContext)
        }
        */
    }
}