package br.com.dio.picpayclone.ui.transferencia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.picpayclone.data.Transferencia
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TransferenciaViewModel(private val apiService: ApiService) : ViewModel() {

    private val _transferencia = MutableLiveData<Transferencia>()
    val transferencia: LiveData<Transferencia> = _transferencia
    val onError = MutableLiveData<String>()

    fun realizaTransferencia(transferencia: Transferencia) {
        viewModelScope.launch {
            try {
                val transferenciaRealziada = apiService.realizarTransferencia(transferencia)
                _transferencia.value = transferenciaRealziada
            } catch (e: Exception) {
                onError.value = "Não Foi possível fazer transferência"
            }
        }
    }
}