package br.com.dio.picpayclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Transacao
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = true)
        observarSaldo()
        observarTransferencias()
        observarErroSaldo()
        observarErroTransferencia()
        observarLoadingSaldo()
        observarLoadingTransferencia()
    }

    private fun observarLoadingSaldo() {
        homeViewModel.onLoadingSaldo.observe(viewLifecycleOwner, Observer { onLoading ->
            if (onLoading) {
                progressBarSaldo.visibility = VISIBLE
                textViewSaldo.visibility = INVISIBLE
                textViewLabelSaldo.visibility = INVISIBLE
            } else {
                progressBarSaldo.visibility = GONE
                textViewSaldo.visibility = VISIBLE
                textViewLabelSaldo.visibility = VISIBLE
            }
        })
    }

    private fun observarLoadingTransferencia() {
        homeViewModel.onLoadingTransferencia.observe(viewLifecycleOwner, Observer { onLoading ->
            if (onLoading) {
                progressBarTransferencia.visibility = VISIBLE
                recyclerView.visibility = GONE
            } else {
                progressBarTransferencia.visibility = GONE
                recyclerView.visibility = VISIBLE
            }
        })
    }

    private fun observarErroTransferencia() {
        homeViewModel.onErrorTransferencia.observe(viewLifecycleOwner, Observer {
            it?.let { mensagem ->
                configuraRecyclerView(mutableListOf())
                Toast.makeText(this.context, mensagem, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observarErroSaldo() {
        homeViewModel.onErrorSaldo.observe(viewLifecycleOwner, Observer {
            it?.let { mensagem ->
                Toast.makeText(this.context, mensagem, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun observarTransferencias() {
        homeViewModel.transferencias.observe(viewLifecycleOwner, Observer {
            it?.let { transferencias ->
                configuraRecyclerView(transferencias)
            }
        })
    }

    private fun configuraRecyclerView(transferencais: List<Transacao>) {
        recyclerView.adapter = HomeAdapter(transferencais)
    }

    private fun observarSaldo() {
        homeViewModel.saldo.observe(viewLifecycleOwner, Observer {
            textViewSaldo.text = it.formatarMoeda()
        })
    }
}