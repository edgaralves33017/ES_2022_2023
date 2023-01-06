package ui.menu

import data.model.Utilizador
import ui.gerirpratos.GerirPratos
import ui.gerirreservas.GerirReservas
import ui.gerirutilizadores.GerirUtilizadores
import ui.login.Login

class Menu(val user: Utilizador) {
    init {
        startMenu()
    }

    private fun startMenu() {
        println("######################################")
        println("Por favor escolha uma das seguintes opções: ")
        println("1 - Gerir pratos;" +
                "2 - Gerir reservas;")
        if (user.isAdmin) {
            println("e - Gerir utilizadores;")
        }
        println("b - Logout;")

        when(readln().trim()) {
            "1" -> {
                GerirPratos(user)
            }
            "2" -> {
                GerirReservas(user)
            }

            "e" -> {
                GerirUtilizadores(user)
            }
            "b" -> {
                Login()
            }
        }
    }
}