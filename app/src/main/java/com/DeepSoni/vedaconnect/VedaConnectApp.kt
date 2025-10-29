package com.DeepSoni.vedaconnect

import android.app.Application
import com.DeepSoni.vedaconnect.repository.RigvedaRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class VedaConnectApp : Application() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()
        GlobalScope.launch(Dispatchers.IO) {
            RigvedaRepository.initialize(applicationContext)
        }
    }
}