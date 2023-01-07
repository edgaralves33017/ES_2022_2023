package ui.menu

import data.model.Utilizador
import ui.gerirpratos.GerirPratos
import ui.gerirreservas.GerirReservas
import ui.gerirutilizadores.GerirUtilizadores
import ui.login.Login
import ui.menu.viewmodel.MenuViewModel

class Menu(val user: Utilizador) {
    private val viewModel = MenuViewModel()
    init {
        startMenu()
    }

    private fun startMenu() {
        println("######################################")
        println("Por favor escolha uma das seguintes opções: ")
        println("1 - Gerir pratos;\n" +
                "2 - Gerir reservas;\n" +
                "3 - DEBUG Menu -> Repor valores originais nas base de dados;\n")
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
            "3" -> {
                viewModel.defaultValues()
                startMenu()
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