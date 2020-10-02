package br.com.dio.picpayclone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Transacao
import br.com.dio.picpayclone.extension.formatarMoeda
import kotlinx.android.synthetic.main.item_transacao.view.*

class HomeAdapter(private val transacoes: List<Transacao> = listOf()) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_transacao,
                parent,
                false
            )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = transacoes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transacao = transacoes[position]
        holder.bind(transacao)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(transacao: Transacao) {
            with(itemView) {
                textViewOrigem.text = transacao.origem.nomeCompleto
                textViewDestino.text = transacao.destino.nomeCompleto
                textViewValor.text = transacao.valor.formatarMoeda()
                textViewData.text = transacao.dataHora
                textViewCirculo.text =
                    transacao.origem.nomeCompleto.first().toUpperCase().toString()
            }
        }
    }

}