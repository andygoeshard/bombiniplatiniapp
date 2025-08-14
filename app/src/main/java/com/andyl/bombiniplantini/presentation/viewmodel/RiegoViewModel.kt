package com.andyl.bombiniplantini.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andyl.bombiniplantini.domain.repository.Esp32Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RiegoViewModel(private val repo: Esp32Repository) : ViewModel() {

    private val _estado = MutableStateFlow("Desconocido")
    val estado: StateFlow<String> = _estado.asStateFlow()

    fun actualizarEstado() {
        viewModelScope.launch {
            try {
                val html = repo.getData()
                // Parse simple: buscar "Humedad: 1234" y "Estado bomba: ..."
                val humedadRegex = "Humedad: (\\d+)".toRegex()
                val estadoRegex = "Estado bomba: (ENCENDIDA|APAGADA)".toRegex()
                val humedad = humedadRegex.find(html)?.groups?.get(1)?.value ?: "N/A"
                val bomba = estadoRegex.find(html)?.groups?.get(1)?.value ?: "N/A"
                _estado.value = "Humedad: $humedad\nBomba: $bomba"
            } catch (e: java.net.UnknownHostException) {
                _estado.value = "ESP32 no encontrado (verificá IP/WiFi)"
            } catch (e: java.net.SocketTimeoutException) {
                _estado.value = "Tiempo de conexión agotado"
            } catch (e: Exception) {
                _estado.value = "Error al conectar con ESP32"
            }
        }
    }

    fun encenderBomba() {
        viewModelScope.launch {
            try {
                repo.bombOn()
                actualizarEstado()
            } catch (e: java.net.UnknownHostException) {
                _estado.value = "ESP32 no encontrado al encender"
            } catch (e: java.net.SocketTimeoutException) {
                _estado.value = "Tiempo de conexión agotado al encender"
            } catch (e: Exception) {
                _estado.value = "Error al encender la bomba"
            }
        }
    }

    fun apagarBomba() {
        viewModelScope.launch {
            try {
                repo.bombOff()
                actualizarEstado()
            } catch (e: java.net.UnknownHostException) {
                _estado.value = "ESP32 no encontrado al apagar"
            } catch (e: java.net.SocketTimeoutException) {
                _estado.value = "Tiempo de conexión agotado al apagar"
            } catch (e: Exception) {
                _estado.value = "Error al apagar la bomba"
            }
        }
    }

}
