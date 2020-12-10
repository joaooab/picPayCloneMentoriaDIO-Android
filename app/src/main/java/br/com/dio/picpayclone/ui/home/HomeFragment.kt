package br.com.dio.picpayclone.ui.home

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
import br.com.dio.picpayclone.data.State
import br.com.dio.picpayclone.data.Transacao
import br.com.dio.picpayclone.data.UsuarioLogado
import br.com.dio.picpayclone.extension.desaparecer
import br.com.dio.picpayclone.extension.esconder
import br.com.dio.picpayclone.extension.formatarMoeda
import br.com.dio.picpayclone.extension.mostrar
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        if (UsuarioLogado.isNaoLogado()) {
            val direcao = HomeFragmentDirections.actionGlobalLoginFragment()
            controlador.navigate(direcao)
            return
        }
        observarEstadoSaldo()
        observarEstadoTransacoes()
    }

    private fun observarEstadoTransacoes() {
        homeViewModel.transacaoState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    mostrarProgresTransacao()
                }
                is State.Success -> {
                    esconderProgresTransacao()
                    configuraRecyclerView(it.data)
                }
                is State.Error -> {
                    esconderProgresTransacao()
                    configuraRecyclerView(mutableListOf())
                    Toast.makeText(this.context, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun observarEstadoSaldo() {
        homeViewModel.saldoState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is State.Loading -> {
                    mostrarProgresSaldo()
                }
                is State.Success -> {
                    esconderProgresSaldo()
                    textViewSaldo.text = it.data.formatarMoeda()
                }
                is State.Error -> {
                    esconderProgresSaldo()
                    Toast.makeText(this.context, it.error.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun esconderProgresSaldo() {
        progressBarSaldo.desaparecer()
        textViewSaldo.mostrar()
        textViewLabelSaldo.mostrar()
    }

    private fun mostrarProgresSaldo() {
        progressBarSaldo.mostrar()
        textViewSaldo.esconder()
        textViewLabelSaldo.esconder()
    }


    private fun mostrarProgresTransacao() {
        progressBarTransferencia.mostrar()
        recyclerView.desaparecer()
    }

    private fun esconderProgresTransacao() {
        progressBarTransferencia.desaparecer()
        recyclerView.mostrar()
    }

    private fun configuraRecyclerView(transferencais: List<Transacao>) {
        recyclerView.adapter = HomeAdapter(transferencais)
    }

}