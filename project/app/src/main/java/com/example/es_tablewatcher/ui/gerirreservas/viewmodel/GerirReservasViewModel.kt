package com.example.es_tablewatcher.ui.gerirreservas.viewmodel

import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.data.model.Reserva

class GerirReservasViewModel {

    fun obterReservas() : List<Reserva>{
        return App.repository.obterReservas().filter { !it.terminated }
    }

    fun obterMesas() : List<Mesa>{
        return App.repository.obterMesas()
    }
}