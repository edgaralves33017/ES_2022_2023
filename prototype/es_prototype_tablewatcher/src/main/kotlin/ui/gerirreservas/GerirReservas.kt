package ui.gerirreservas

import data.model.Prato
import data.model.Utilizador
import ui.gerirreservas.viewmodel.GerirReservasViewModel
import ui.menu.Menu

class GerirReservas(private val user: Utilizador) {
    private val viewModel = GerirReservasViewModel()
    init {
        startMenu()
    }

    private fun startMenu() {
        println("######################################")
        println("Escolha uma das seguintes opções:")
        println("1 - Listar todas as reservas;\n" +
                "2 - Listar mesas disponíveis;\n" +
                "3 - Criar reserva;\n" +
                "4 - Adicionar prato a reserva;\n" +
                "5 - Remover prato a reserva;\n" +
                "6 - Terminar reserva;\n" +
                "7 - Calcular total da reserva;")

        println("b - Menu anterior.")

        when(readln().trim()) {
            "1" -> {
                startListarReservas()
            }
            "2" -> {
                startListarMesasDisponiveis()
            }
            "3" -> {
                startCriarReserva()
            }
            "4" -> {
                startAdicionarPratoAReserva()
            }
            "5" -> {
                startRemoverPratoAReserva()
            }
            "6" -> {
                startTerminarReserva()
            }
            "7" -> {
                startCalcularTotalReserva()
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

    private fun startCalcularTotalReserva() {
        val listaReservas = viewModel.obterReservas()
        if (listaReservas.isNotEmpty()) {
            viewModel.obterReservas().forEach {
                if (it.total < 0.0) {
                    println("[${it.id}] " +
                            "::> Data: ${it.data} " +
                            "::> Contacto Cliente: ${it.contactoCliente} " +
                            "::> Nome Cliente: ${it.nomeClient} " +
                            "::> Mesa: ${it.mesaId}")
                    if(it.listaPratos.isNotEmpty()) {
                        println("::> Pratos:")
                        it.listaPratos.forEach { prato ->
                            println(":::>${prato.descricao} :::> Preço: ${prato.preco}")
                        }
                    }
                    println("++++++++++++++++++++++++++++++++++++")
                }
            }
            println("Introduza o ID da reserva para calcular o preço total: ")
            val reservaID = readln()
            try{
                println("Total: ${viewModel.calcularTotalReserva(reservaID.toInt())}")
                startMenu()
            }catch(e: Exception) {
                println("ID inválido. Tente outra vez!")
                startCalcularTotalReserva()
            }
        }
        else {
            println("Sem reservas disponíveis!")
            startMenu()
        }

    }

    private fun startTerminarReserva() {
        val listaReservas = viewModel.obterReservas()
        if (listaReservas.isNotEmpty()) {
            listaReservas.forEach {
                if (it.total < 0.0) {
                    println("[${it.id}] " +
                            "::> Data: ${it.data} " +
                            "::> Contacto Cliente: ${it.contactoCliente} " +
                            "::> Nome Cliente: ${it.nomeClient} " +
                            "::> Mesa: ${it.mesaId}")
                    if(it.listaPratos.isNotEmpty()) {
                        println("::> Pratos:")
                        it.listaPratos.forEach { prato ->
                            println(":::>${prato.descricao} :::> Preço: ${prato.preco}")
                        }
                    }
                    println("++++++++++++++++++++++++++++++++++++")
                }
            }
            println("Introduza o ID da reserva para terminar: ")
            val reservaID = readln()
            try{
                println("Reserva terminar com sucesso!")
                println("Total: ${viewModel.fecharReserva(reservaID.toInt())}")
                startMenu()
            }catch(e: Exception) {
                println("ID inválido. Tente outra vez!")
                startTerminarReserva()
            }
        }
        else {
            println("Sem reservas disponíveis!")
            startMenu()
        }

    }

    private fun startAdicionarPratoAReserva() {
        val listaReservas = viewModel.obterReservas()
        if (listaReservas.isNotEmpty()) {
            println("Lista de reservas: ")

            listaReservas.forEach {
                if (it.total < 0.0) {
                    println("[${it.id}] " +
                            "::> Data: ${it.data} " +
                            "::> Contacto Cliente: ${it.contactoCliente} " +
                            "::> Nome Cliente: ${it.nomeClient} " +
                            "::> Mesa: ${it.mesaId}")
                    println("::> Pratos:")
                    it.listaPratos.forEach { prato ->
                        println(":::>${prato.descricao} :::> Preço: ${prato.preco}")
                    }
                    println("++++++++++++++++++++++++++++++++++++")
                }
            }
            println("Por favor selecione uma reserva: ")
            try {
                val reservaID = readln().toInt()
                val pratosSelecionados = selecionarPratos(mutableListOf(), viewModel.obterPratos())
                if (pratosSelecionados.isEmpty()) {
                    println("Sem pratos adicionados!")
                    startMenu()
                }
                if(viewModel.adicionarPratoAReserva(reservaID, pratosSelecionados)) {
                    println("Pratos adicionados com sucesso!")
                    startMenu()
                }
                else {
                    println("Ocorreu um erro! Tente outra vez!")
                    startAdicionarPratoAReserva()
                }
            }catch (e: Exception) {
                println("ID inválido! Tente outra vez!")
                startAdicionarPratoAReserva()
            }
        }
        else {
            println("Sem reservas disponíveis!")
            startMenu()
        }
    }

    private fun startRemoverPratoAReserva() {
        val listaReservas = viewModel.obterReservas()
        if (listaReservas.isNotEmpty()) {
            println("Lista de reservas: ")

            listaReservas.forEach {
                if (it.total < 0.0) {
                    println("[${it.id}] " +
                            "::> Data: ${it.data} " +
                            "::> Contacto Cliente: ${it.contactoCliente} " +
                            "::> Nome Cliente: ${it.nomeClient} " +
                            "::> Mesa: ${it.mesaId}")
                    println("::> Pratos:")
                    it.listaPratos.forEach { prato ->
                        println(":::>${prato.descricao} :::> Preço: ${prato.preco}")
                    }
                    println("++++++++++++++++++++++++++++++++++++")
                }
            }
            println("Por favor selecione uma reserva ou selecione 'b' para cancelar: ")
            try {
                val reservaID = readln()
                if (reservaID == "b") startMenu()
                else {
                    val pratosSelecionados = selecionarPratos(mutableListOf(), viewModel.obterPratos())
                    if(viewModel.removerPratoAReserva(reservaID.toInt(), pratosSelecionados)) {
                        println("Pratos removidos com sucesso!")
                        startMenu()
                    }
                    else {
                        println("Ocorreu um erro! Tente outra vez!")
                        startAdicionarPratoAReserva()
                    }
                }
            }catch (e: Exception) {
                println("ID inválido! Tente outra vez!")
                startAdicionarPratoAReserva()
            }
        }
        else {
            println("Sem reservas disponíveis!")
            startMenu()
        }
    }

    private fun selecionarPratos(pratosSelecionados: MutableList<Prato>, pratosDB: List<Prato>) : List<Prato> {
        if (pratosDB.isEmpty()) return pratosSelecionados
        println("Pratos disponíveis: ")
        pratosDB.forEach {
            println("[${it.id}] ::> ${it.descricao} ::> ${it.preco}")
        }
        if (pratosSelecionados.isNotEmpty()) {
            println("Pratos selecionados: ")
            pratosSelecionados.forEach {
                println("[${it.id}] ::> ${it.descricao} ::> ${it.preco}")
            }
        }
        println("Por favor selecione um prato ou 'b' para parar: ")
        val selection = readln()
        return if (selection == "b") pratosSelecionados
        else {
            try {
                pratosSelecionados.add(pratosDB[selection.toInt()])
                selecionarPratos(pratosSelecionados, pratosDB)
            }catch (e: Exception) {
                println("ID inválido! Tente outra vez!")
                selecionarPratos(pratosSelecionados, pratosDB)
            }
        }
    }

    private fun startCriarReserva() {
        val mesasDisponiveis = viewModel.obterMesas()
        if (mesasDisponiveis.isNotEmpty()) {
            println("")
            println("Mesas disponíveis: ")
            mesasDisponiveis.forEach{
                println("[${it.id}] " +
                        "::> Lugares: ${it.lugares} ")
                println("++++++++++++++++++++++++++++++++++++")
            }
            println("Introduza ID da mesa ou 'b' para cancelar: ")
            val mesaID = readln()
            if (mesaID == "b") {
                startMenu()
                return
            }
            println("Introduza o nome do cliente ou 'b' para cancelar: ")
            val nomeCliente = readln()
            if (nomeCliente == "b") {
                startMenu()
                return
            }
            println("Introduza o contacto do cliente ou 'b' para cancelar: ")
            val contactoCliente = readln()
            if (contactoCliente == "b") {
                startMenu()
                return
            }
            try {
                if(viewModel.adicionarReserva(mesaID.toInt(), nomeCliente, contactoCliente)) {
                    println("Reserva adicionada com sucesso!")
                    startMenu()
                }
                else {
                    println("Ocorreu um erro! Por favor tente novamente!")
                    startCriarReserva()
                }
            }catch(e: Exception) {
                println("ID inválido. Tente outra vez!")
                startCriarReserva()
            }
        }
        else {
            println("Sem mesas disponíveis!")
            startMenu()
        }
    }

    private fun startListarMesasDisponiveis() {
        val mesasDisponiveis = viewModel.obterMesas()
        if (mesasDisponiveis.isNotEmpty()) {
            mesasDisponiveis.forEach{
                println("[${it.id}] " +
                        "::> Lugares: ${it.lugares} ")
                println("++++++++++++++++++++++++++++++++++++")
            }
        }
        else {
            println("Sem mesas disponíveis!")
        }

        startMenu()
    }

    private fun startListarReservas() {
        val listReservas = viewModel.obterReservas()
        if (listReservas.isNotEmpty()) {
            listReservas.forEach {
                if (it.total < 0.0) {
                    println("[${it.id}] " +
                            "::> Data: ${it.data} " +
                            "::> Contacto Cliente: ${it.contactoCliente} " +
                            "::> Nome Cliente: ${it.nomeClient} " +
                            "::> Mesa: ${it.mesaId}")
                    if(it.listaPratos.isNotEmpty()) {
                        println("::> Pratos:")
                        it.listaPratos.forEach { prato ->
                            println(":::>${prato.descricao} :::> Preço: ${prato.preco}")
                        }
                    }
                    println("++++++++++++++++++++++++++++++++++++")
                }
            }
        }
        else
            println("Sem reservas disponíveis!")

        startMenu()
    }
}