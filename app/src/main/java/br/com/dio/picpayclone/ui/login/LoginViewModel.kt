package br.com.dio.picpayclone.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.launch

class LoginViewModel(private val service: ApiService) : ViewModel() {

    private val _usuario = MutableLiveData<Usuario>()
    val usuario: LiveData<Usuario> = _usuario
    val onError = MutableLiveData<String>()

    fun login(usurio: String) {
        viewModelScope.launch {
            try {
                val usuario = service.getUsuario(usurio)
                _usuario.value = usuario
            } catch (e: Exception) {
                onError.value = e.message
            }
        }
    }

}