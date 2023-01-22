package com.example.es_tablewatcher.ui.gerirreservas.viewmodel

import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.data.model.Reserva
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AdicionarReservaViewModel {

    fun obterMesas() : List<Mesa>{
        return App.repository.obterMesas()
    }

    fun adicionarReserva(mesaID: Int, nomeCliente: String, contactoCliente:String) : Boolean{
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val currentDate = LocalDateTime.now().format(formatter)
        val reservaList = App.repository.obterReservas()
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
        return App.repository.adicionarReserva(reserva)
    }
}