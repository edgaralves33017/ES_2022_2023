package data.model

data class Reserva(
    val id : Int,
    val data: String,
    val nomeClient: String,
    val contactoCliente: String,
    val listaPratos: MutableList<Prato>,
    var total: Double
)