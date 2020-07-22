package br.com.dio.picpayclone.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.dio.picpayclone.data.UsuarioLogado

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        verificarSeEstaLogado()
    }

    private fun verificarSeEstaLogado() {
        if (UsuarioLogado.isUsuarioLogado()) {
            vaiParaLogin()
        }
    }

    private fun vaiParaLogin() {

    }
}