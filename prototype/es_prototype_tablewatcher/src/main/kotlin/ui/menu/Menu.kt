package ui.menu

import data.model.Prato
import ui.menu.viewmodel.MenuViewModel

class Menu {
    init {

        val viewModel = MenuViewModel()

        println("Retrieving users...")
        viewModel.obterUtilizadores().forEach { println(it.username) }
        println("Writing new prato")
        viewModel.adicionarPrato(Prato(1, "teste", 12.0))
        println("Get pratos")
        viewModel.obterPratos().forEach { println("${it.id}//${it.descricao}//${it.preco}") }
    }
}