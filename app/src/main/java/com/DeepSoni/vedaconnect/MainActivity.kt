package com.DeepSoni.vedaconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import com.DeepSoni.vedaconnect.repository.RigvedaRepository
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        lifecycleScope.launch(Dispatchers.IO) {
            RigvedaRepository.initialize(applicationContext)
        }

        setContent {
            VedaConnectTheme {
                AppNavigation()
            }
        }
    }
}