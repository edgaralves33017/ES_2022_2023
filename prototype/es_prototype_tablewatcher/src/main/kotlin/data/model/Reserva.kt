package data.model

data class Reserva(
    val id : Int,
    val mesaId: Int,
    val data: String,
    val nomeClient: String,
    val contactoCliente: String,
    val listaPratos: MutableList<Prato>,
    var terminated: Boolean,
    var total: Double
)