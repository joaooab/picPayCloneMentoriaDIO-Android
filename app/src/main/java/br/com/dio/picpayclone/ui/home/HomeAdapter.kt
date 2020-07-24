package br.com.dio.picpayclone.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.picpayclone.R
import br.com.dio.picpayclone.data.Transferencia
import br.com.dio.picpayclone.extension.formatarMoeda
import br.com.dio.picpayclone.extension.formatar
import kotlinx.android.synthetic.main.item_transacao.view.*

class HomeAdapter(val transacoes: List<Transferencia>) :
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

        fun bind(transferencia: Transferencia) {
            with(itemView) {
                textViewOrigem.text = transferencia.origem.nomeCompleto
                textViewDestino.text = transferencia.destino.nomeCompleto
                textViewValor.text = transferencia.valor.formatarMoeda()
                textViewData.text = transferencia.dataHora
                textViewCirculo.text = transferencia.origem.nomeCompleto.first().toUpperCase().toString()
            }
        }
    }

}