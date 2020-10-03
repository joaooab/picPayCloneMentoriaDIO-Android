package br.com.dio.picpayclone.ui.ajuste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.UsuarioLogado
import kotlinx.android.synthetic.main.fragment_ajuste.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AjusteFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val ajusteViewModel: AjusteViewModel by viewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ajuste, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        configuraBotaoSair()
        configuraDadosUsuario()
    }

    private fun configuraDadosUsuario() {
        UsuarioLogado.usuario.let { usuario ->
            textViewLoginPrincipal.text = usuario.login
            textViewNomeCompleto.text = usuario.nomeCompleto
            textViewLogin.text = usuario.login
            textViewEmail.text = usuario.email
            textViewNumero.text = usuario.numeroTelefone
        }
    }

    private fun configuraBotaoSair() {
        buttonSair.setOnClickListener {

        }
    }
}