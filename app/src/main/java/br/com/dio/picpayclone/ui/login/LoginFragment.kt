package br.com.dio.picpayclone.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Usuario
import br.com.dio.picpayclone.data.UsuarioLogado
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

class LoginFragment : Fragment() {

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
    }

    private fun configuraLogin() {
        button.setOnClickListener {
            UsuarioLogado.usuario = Usuario("teste", "teste")
            val direcao = LoginFragmentDirections.actionLoginFragmentToNavigationHome()
            controlador.navigate(direcao)
        }
    }
}