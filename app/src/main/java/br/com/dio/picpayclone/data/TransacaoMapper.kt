package br.com.dio.picpayclone.data

fun TransacaoNetwork.toModel(): Transacao = Transacao(
    codigo = codigo.orEmpty(),
    origem = origem ?: Usuario(),
    destino = destino ?: Usuario(),
    dataHora = dataHora.orEmpty(),
    isCartaoCredito = isCartaoCredito ?: false,
    valor = valor ?: 0.0,
    cartaoCredito = cartaoCredito ?: CartaoCredito()
)

fun List<TransacaoNetwork>.toModel() = this.map { it.toModel() }