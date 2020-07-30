package br.com.dio.picpayclone.data.transacao

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import br.com.dio.picpayclone.services.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class TransacaoDataSource(
    private val apiService: ApiService,
    private val scope: CoroutineScope,
    private val login: String,
    private val onLoading: MutableLiveData<Boolean>,
    private val onError: MutableLiveData<String>
) : PageKeyedDataSource<Int, Transacao>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Transacao>
    ) {
        scope.launch {
            onLoading.value = true
            try {
                val transacoes = apiService.getTransacoes(login, 0)
                callback.onResult(transacoes.content, null, 1)
            } catch (e: Exception) {
                onError.value = e.message
            }
            onLoading.value = false
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Transacao>) {
        scope.launch {
            try {
                val page = params.key
                val transacoes = apiService.getTransacoes(login, page)
                callback.onResult(transacoes.content, page.plus(1))
            } catch (e: Exception) {
                onError.value = e.message
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Transacao>) {
        TODO("Not yet implemented")
    }

    override fun invalidate() {
        super.invalidate()
        scope.cancel()
    }

}