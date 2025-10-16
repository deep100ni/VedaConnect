package com.DeepSoni.vedaconnect.feature.suktas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.DeepSoni.vedaconnect.data.Sukta
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MandalaOneSuktasViewModel(application: Application) : AndroidViewModel(application) {

    private val _suktas = MutableStateFlow<List<Sukta>>(emptyList())
    val suktas: StateFlow<List<Sukta>> = _suktas

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // ✅ CHANGE: The init block is now removed. We will load data manually.
    // init {
    //     loadSukas()
    // }

    // ✅ CHANGE: This function now accepts an ID and filters the data.
    fun loadSukasForMandala(mandalaId: Int) {
        if (_suktas.value.isNotEmpty()) return // Avoid reloading if already loaded

        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.value = true
            // Ensure repository is initialized
            MandalaOneSuktasRepository.initialize(getApplication())

            // Filter the full list of suktas to get only the ones for the requested mandala
            val filteredSutkas = MandalaOneSuktasRepository.suktas.filter {
                it.mandalaNumber == mandalaId
            }

            _suktas.value = filteredSutkas
            _isLoading.value = false
        }
    }
}