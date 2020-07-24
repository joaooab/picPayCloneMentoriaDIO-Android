package br.com.dio.picpayclone.ui.transferencia

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import br.com.dio.picpayclone.Componentes
import br.com.dio.picpayclone.ComponentesViewModel
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.*
import br.com.dio.picpayclone.extension.DATE_TIME_FORMAT_US
import br.com.dio.picpayclone.extension.formatar
import kotlinx.android.synthetic.main.fragment_transferencia.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class TransferenciaFragment : Fragment() {

    private val componentesViewModel: ComponentesViewModel by sharedViewModel()
    private val transferenciaViewModel: TransferenciaViewModel by viewModel()
    private val argumentos by navArgs<TransferenciaFragmentArgs>()
    private val usuario by lazy { argumentos.usuario }
    private val controlador by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transferencia, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        componentesViewModel.temComponentes = Componentes(bottomNavigation = false)
        configuraDadosUsuario()
        configuraRadioGroup()
        configuraBotaoTransferir()
        observarTransferencia()
        observarErro()
    }

    private fun observarErro() {
        transferenciaViewModel.onError.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun observarTransferencia() {
        transferenciaViewModel.transferencia.observe(viewLifecycleOwner, Observer {
            val direcao =
                TransferenciaFragmentDirections.actionNavigationTransferenciaToNavigationPagar()
            controlador.navigate(direcao)
        })
    }

    private fun configuraBotaoTransferir() {
        buttonTransferir.setOnClickListener {
            val transferencia = criarTransferencia()
            transferenciaViewModel.realizaTransferencia(transferencia)
        }
    }

    private fun criarTransferencia(): Transferencia {
        val usuarioOrigem = UsuarioLogado.usuario
            ?: throw  IllegalArgumentException("Usuario não está logado")
        val cartaoCredito = criarCartaoCredito(usuarioOrigem)
        val dataHora = Calendar.getInstance().formatar(DATE_TIME_FORMAT_US)
        val valor = editTextValor.text.toString().toDouble()
        return Transferencia("", usuarioOrigem, usuario, dataHora, true, valor, cartaoCredito)
    }

    private fun criarCartaoCredito(usuarioOrigem: Usuario): CartaoCredito {
        val numeroCartao = editTextNumeroCartao.text.toString()
        val titular = editTextTitular.text.toString()
        val dataExpiracao = editTextVencimento.getData()?.formatar() ?: ""
        val cvv = editTextCVV.text.toString()
        val cartaoCredito = CartaoCredito(
            BandeiraCartao.VISA,
            numeroCartao,
            titular,
            dataExpiracao,
            cvv,
            "",
            usuarioOrigem
        )
        return cartaoCredito
    }

    private fun configuraDadosUsuario() {
        textViewNome.text = usuario.login
        textViewNomeCompleto.text = usuario.nomeCompleto
    }

    private fun configuraRadioGroup() {
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonCartaoCredito -> {
                    constraintLayoutCartaoCredito.visibility = VISIBLE
                }
                else -> {
                    constraintLayoutCartaoCredito.visibility = GONE
                }
            }
        }
    }
}