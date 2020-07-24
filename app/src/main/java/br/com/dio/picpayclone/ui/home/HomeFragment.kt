package br.com.dio.picpayclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Transferencia
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.extension.formatarMoeda
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val homeViewModel: HomeViewModel by viewModel()
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (UsuarioLogado.isUsuarioNaoLogado()) {
            val direcao = HomeFragmentDirections.actionGlobalNavigationLogin()
            controlador.navigate(direcao)
            return
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        observarSaldo()
//        configuraRecyclerView()
    }

    private fun configuraRecyclerView() {
        val mockLista = listOf(Transferencia("1", valor = 10.0), Transferencia("2", valor = 20.0))
        recyclerView.adapter = HomeAdapter(mockLista)
    }

    private fun observarSaldo() {
        homeViewModel.saldo.observe(viewLifecycleOwner, Observer {
            textViewSaldo.text = it.formatarMoeda()
        })
    }
}