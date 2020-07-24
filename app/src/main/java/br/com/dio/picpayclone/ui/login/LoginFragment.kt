package br.com.dio.picpayclone.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.UsuarioLogado
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModel()
    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = false)
        configuraLogin()
        observaUsuario()
        observaErro()
    }

    private fun observaErro() {
        loginViewModel.onError.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observaUsuario() {
        loginViewModel.usuario.observe(viewLifecycleOwner, Observer {
            UsuarioLogado.usuario = it
            val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
            controlador.navigate(direcao)
        })
    }

    private fun configuraLogin() {
        button.setOnClickListener {
            val usurio = editTextUsuario.text.toString()
            loginViewModel.login(usurio)
        }
    }
}