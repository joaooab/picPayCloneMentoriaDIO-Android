import androidx.recyclerview.widget.DiffUtil
import br.com.dio.picpayclone.data.transacao.Transacao

class TransacaoDiffUtilCallBack : DiffUtil.ItemCallback<Transacao>() {
    override fun areItemsTheSame(oldItem: Transacao, newItem: Transacao): Boolean {
        return oldItem.codigo == newItem.codigo
    }

    override fun areContentsTheSame(oldItem: Transacao, newItem: Transacao): Boolean {
        return oldItem == newItem
    }
}