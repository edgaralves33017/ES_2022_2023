package ui.menu.viewmodel

import data.Repository
import data.model.Mesa
import data.model.Prato
import data.model.Reserva
import data.model.Utilizador

class MenuViewModel {
    private var repository: Repository = Repository()

    fun obterPratos() : List<Prato> {
        return repository.obterPratos();
    }

    fun obterUtilizadores() : List<Utilizador>{
        return repository.obterUtilizadores()
    }

    fun obterReservas() : List<Reserva>{
        return repository.obterReservas()
    }

    fun obterMesas() : List<Mesa>{
        return repository.obterMesas()
    }

    fun adicionarPrato(prato:Prato) : Boolean{
        return repository.adicionarPrato(prato)
    }

    fun adicionarUtilizador(utilizador: Utilizador) : Boolean{
        return repository.adicionarUtilizador(utilizador)
    }

    fun adicionarReserva(reserva: Reserva) : Boolean{
        return repository.adicionarReserva(reserva)
    }
}