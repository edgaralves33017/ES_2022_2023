package com.example.es_tablewatcher.ui.gerirreservas.viewmodel

import androidx.recyclerview.widget.RecyclerView
import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Prato
import com.example.es_tablewatcher.data.model.Reserva

class GerirPratosAReservaViewModel {
    fun obterPratos() : List<Prato> {
        return App.repository.obterPratos()
    }
    private fun removerPratoAReserva(reservaID: Int, pratos: List<Prato>) : Boolean {
        return App.repository.removerPratoAReserva(reservaID, pratos)
    }
    private fun adicionarPratoAReserva(id: Int, pratos: List<Prato>) : Boolean {
        return App.repository.adicionarPratos(id, pratos)
    }
    fun fecharReserva(id: Int) : Double {
        return App.repository.fecharReserva(id)
    }

    fun calcularTotalReserva(id: Int) : Double {
        return App.repository.calcularTotalReserva(id)
    }

    fun removerPratoDaReserva (pratosNaReserva: MutableList<Prato>, reserva: Reserva, prato: Prato, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        removerPratoAReserva(reserva.id, listOf(prato))
        pratosNaReserva.remove(prato)
        adapter?.notifyDataSetChanged()
    }

    fun adicionarPratoAReserva (pratosNaReserva: MutableList<Prato>, reserva: Reserva, prato: Prato, adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?) {
        adicionarPratoAReserva(reserva.id, listOf(prato))
        pratosNaReserva.add(prato)
        adapter?.notifyDataSetChanged()
    }
}