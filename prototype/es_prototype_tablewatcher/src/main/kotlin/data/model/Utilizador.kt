package data.model

data class Utilizador(
    val id : Int,
    val username: String,
    val password: String,
    val isAdmin: Boolean
)