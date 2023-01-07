package ui.gerirutilizadores

import data.model.Utilizador
import ui.gerirutilizadores.viewmodel.GerirUtilizadoresViewModel
import ui.menu.Menu
import java.util.*

class GerirUtilizadores(val user: Utilizador) {
    private val viewModel = GerirUtilizadoresViewModel()
    init {
        startMenu()
    }

    private fun startMenu() {
        println("######################################")
        println("Escolha uma das seguintes opções:")
        println("1 - Listar todos os utilizadores;")
        if (user.isAdmin) {
            println("2 - Adicionar utilizador;\n" +
                    "3 - Remover utilizador")
        }

        println("b - Menu anterior.")

        when(readln().trim()) {
            "1" -> {
                startListarUtilizadores()
            }
            "2" -> {
                if (user.isAdmin)
                    startAdicionarUtilizadores()
                else {
                    println("Escolha inválida!")
                    startMenu()
                }
            }
            "3" -> {
                if (user.isAdmin)
                    startRemoverUtilizador()
                else {
                    println("Escolha inválida!")
                    startMenu()
                }
            }
            "b" -> {
                Menu(user)
            }
            else -> {
                println("Escolha inválida!")
                startMenu()
            }
        }
    }

    private fun startListarUtilizadores() {
        println("")
        viewModel.obterUtilizadores().forEach { println("ID: [${it.id}] ::> Username: ${it.username} ::> Admin?: ${it.isAdmin}") }
        startMenu()
    }

    private fun startAdicionarUtilizadores() {
        println("")
        println("Introduza o nome de utilizador: ")
        val username = readln()
        println("Introduza a password: ")
        val password = readln()
        println("Utilizador é admin?: Y ou N (default)")
        val isAdmin = readln()
        val isAdminBool = if (isAdmin.uppercase() != "Y" && isAdmin.uppercase() != "N") {
            false
        } else {
            isAdmin.uppercase() == "Y"
        }
        viewModel.adicionarUtilizador(username, password, isAdminBool)
        println("Utilizador adicionado com sucesso!")
        startMenu()
    }

    private fun startRemoverUtilizador() {
        println("")
        val listUtilizadores = viewModel.obterUtilizadores()
        listUtilizadores.forEach { println("ID: [${it.id}] ::> Username: ${it.username} ::> Admin?: ${it.isAdmin}") }
        println("Indique o ID do utilizador que deseja remover ou 'b' para voltar para trás: ")
        val idToRemove = readln()
        val utilizadorToRemove = listUtilizadores.filter { it.id ==  idToRemove.toInt()}
        if (utilizadorToRemove.isEmpty()) {
            println("ID incorrecto!")
            startRemoverUtilizador()
        }
        else {
            if (viewModel.removerUtilizador(utilizadorToRemove.first().id))
                println("Utilizador removido com sucesso!")
            else
                println("Ocorreu um erro! Por favor tente novamente!")

            startRemoverUtilizador()
        }
    }
}