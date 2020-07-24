package br.com.dio.picpayclone.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.picpayclone.data.Transferencia
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.launch

class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _saldo = MutableLiveData(0.0)
    val saldo: LiveData<Double> = _saldo
    private val _transferencias = MutableLiveData<List<Transferencia>>()
    val transferencias: LiveData<List<Transferencia>> = _transferencias
    val onErrorSaldo = MutableLiveData<String>()
    val onErrorTransferencia = MutableLiveData<String>()

    init {
        if (UsuarioLogado.isUsuarioLogado()) {
            viewModelScope.launch {
                val login = UsuarioLogado.usuario.login
                obterSaldo(login)
                obterHistorico(login)
            }
        }
    }

    private suspend fun obterHistorico(login: String) {
        try {
            val historico = apiService.getTransferencias(login)
            _transferencias.value = historico
        } catch (e: Exception) {
            onErrorTransferencia.value = e.message
        }
    }

    private suspend fun obterSaldo(login: String) {
        try {
            val novoSaldo = apiService.getSaldo(login).saldo
            UsuarioLogado.setSaldo(novoSaldo)
            _saldo.value = novoSaldo
        } catch (e: Exception) {
            onErrorSaldo.value = e.message
        }
    }

}