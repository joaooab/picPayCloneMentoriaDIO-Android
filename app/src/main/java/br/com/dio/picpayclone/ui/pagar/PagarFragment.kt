package br.com.dio.picpayclone.ui.pagar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Usuario
import kotlinx.android.synthetic.main.fragment_pagar.*
import org.koin.android.viewmodel.ext.android.viewModel

class PagarFragment : Fragment() {

    private val pagarViewModel: PagarViewModel by viewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pagar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val list = listOf(
            Usuario("joaoovitor", "123", "joao@gmail", "Joao Vitor Freitas"),
            Usuario("cicerobuild", "123", "cicero@gmail", "Cicero Build")
        )
        recyclerView.adapter = PagarAdapter(list) {
            val direcao = PagarFragmentDirections.actionNavigationPagarToNavigationTransferencia(it)
            controlador.navigate(direcao)
        }
    }
}