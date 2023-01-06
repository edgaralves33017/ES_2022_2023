package ui.gerirreservas.viewmodel

import data.Repository
import data.model.Mesa
import data.model.Prato
import data.model.Reserva

class GerirReservasViewModel {
    private var repository: Repository = Repository()

    fun obterReservas() : List<Reserva>{
        return repository.obterReservas()
    }

    fun obterMesas() : List<Mesa>{
        return repository.obterMesas()
    }

    fun adicionarReserva(reserva: Reserva) : Boolean{
        return repository.adicionarReserva(reserva)
    }

    fun adicionarPratosAReserva(reserva: Reserva, pratos: List<Prato>) : Boolean {
        return repository.adicionarPratos(reserva, pratos)
    }

    fun fecharReserva(reserva: Reserva) : Double {
        return repository.fecharReserva(reserva)
    }
}