package br.com.dio.picpayclone.ui.login

import androidx.lifecycle.ViewModel
import br.com.dio.picpayclone.data.UsuarioLogado

class LoginViewModel : ViewModel() {

    fun isUsuarioLogado() = UsuarioLogado.isUsuarioLogado()

    fun isUsuarioNaoLogado() = !isUsuarioLogado()

}