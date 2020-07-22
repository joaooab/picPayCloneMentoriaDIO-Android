package br.com.dio.picpayclone.data

data class CartaoCredito(
    val bandeira: BandeiraCartao = BandeiraCartao.MASTERCARD,
    val codigoSeguranca: String = "123",
    val dataExpiracao: String = "01/01/2025",
    val nomeTitular: String = "teste",
    val numeroCartao: String = "",
    val numeroToken: String = "",
    val usuario: Usuario = Usuario("teste", "teste"),
    val isSalvar: Boolean = false
)
