package ui.gerirpratos

import data.model.Prato
import data.model.Utilizador
import ui.gerirpratos.viewmodel.GerirPratosViewModel
import ui.menu.Menu

class GerirPratos(val user:Utilizador) {
    private val viewModel = GerirPratosViewModel()
    init {
        startMenu()
    }

    private fun startMenu() {
        println("######################################")
        println("Escolha uma das seguintes opções:")
        println("1 - Listar todos os pratos;")
        if(user.isAdmin) {
            println ("2 - Adicionar prato;\n" +
                    "3 - Remover prato;")
        }
        println("b - Menu anterior.")

        when(readln().trim()) {
            "1" -> {
                startListarPratos()
            }
            "2" -> {
                if (user.isAdmin)
                    startAdicionarPrato()
                else {
                    println("Escolha inválida!")
                    startMenu()
                }
            }
            "3" -> {
                if (user.isAdmin)
                    startRemoverPrato()
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

    private fun startListarPratos() {
        println("")
        val pratos = viewModel.obterPratos()
        if(pratos.isEmpty())
            println("Sem pratos disponíveis!")
        else
            pratos.forEach { println("[${it.id}] ::> ${it.descricao} ::> ${it.preco}") }
        startMenu()
    }

    private fun startAdicionarPrato() {
        println("")
        println("Introduza a descrição do prato: ")
        val desc = readln()
        println("Introduza o preço do prato: ")
        val preco = readln()
        viewModel.adicionarPrato(desc, preco)
        println("Prato adicionado com sucesso!")
        startMenu()
    }

    private fun startRemoverPrato() {
        println("")
        val listPratos = viewModel.obterPratos()
        listPratos.forEach { println("[${it.id}] ::> ${it.descricao} ::> ${it.preco}") }
        println("Indique o ID do prato que deseja remover ou '/b'/ para voltar para trás: ")
        val idToRemove = readln()
        val pratoToRemove = listPratos.filter { it.id ==  idToRemove.toInt()}
        if (pratoToRemove.isEmpty()) {
            println("ID incorrecto!")
            startRemoverPrato()
        }
        else {
            if(viewModel.removerPrato(pratoToRemove.first().id))
                println("Prato removido com sucesso!")
            else
                println("Ocorreu um erro! Por favor tente novamente!")

            startMenu()
        }
    }
}