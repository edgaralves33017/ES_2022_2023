package com.example.es_tablewatcher.ui.gerirreservas.viewmodel

import com.example.es_tablewatcher.data.Repository
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.data.model.Prato
import com.example.es_tablewatcher.data.model.Reserva
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GerirReservasViewModel {
    private var repository: Repository = Repository()

    fun obterReservas() : List<Reserva>{
        return repository.obterReservas().filter { !it.terminated }
    }

    fun obterMesas() : List<Mesa>{
        return repository.obterMesas()
    }

    fun adicionarReserva(mesaID: Int, nomeCliente: String, contactoCliente:String) : Boolean{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val currentDate = LocalDateTime.now().format(formatter)
        val reservaList = repository.obterReservas()
        val newID = if (reservaList.isEmpty()) 0 else reservaList.last().id+1
        val reserva = Reserva(
            newID,
            mesaID,
            currentDate,
            nomeCliente,
            contactoCliente,
            mutableListOf(),
            false,
            -1.0
        )
        return repository.adicionarReserva(reserva)
    }

    fun adicionarPratoAReserva(id: Int, pratos: List<Prato>) : Boolean {
        return repository.adicionarPratos(id, pratos)
    }

    fun fecharReserva(id: Int) : Double {
        return repository.fecharReserva(id)
    }

    fun calcularTotalReserva(id: Int) : Double {
        return repository.calcularTotalReserva(id)
    }
    fun obterPratos() : List<Prato> {
        return repository.obterPratos()
    }
    fun removerPratoAReserva(reservaID: Int, pratos: List<Prato>) : Boolean {
        return repository.removerPratoAReserva(reservaID, pratos)
    }
}