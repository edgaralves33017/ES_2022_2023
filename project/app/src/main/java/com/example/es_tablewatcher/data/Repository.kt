package com.example.es_tablewatcher.data

import android.content.Context
import com.example.es_tablewatcher.data.local.LocalRepository
import com.example.es_tablewatcher.data.model.Mesa
import com.example.es_tablewatcher.data.model.Prato
import com.example.es_tablewatcher.data.model.Reserva
import com.example.es_tablewatcher.data.model.Utilizador

class Repository(context: Context) {
      private var db : LocalRepository = LocalRepository(context)

      fun login (username: String, password: String) : Utilizador? {
            return db.login(username, password)
      }

      fun obterPratos() : List<Prato> {
            return db.obterPratos()
      }

      fun obterUtilizadores() : List<Utilizador> {
            return db.obterUtilizadores()
      }

      fun obterReservas() : List<Reserva>{
            return db.obterReservas()
      }

      fun obterMesas() : List<Mesa>{
            return db.obterMesas()
      }

      fun adicionarPrato(prato:Prato) : Boolean{
            return db.adicionarPrato(prato)
      }

      fun adicionarUtilizador(utilizador: Utilizador) : Boolean{
            return db.adicionarUtilizador(utilizador)
      }

      fun adicionarReserva(reserva: Reserva) : Boolean{
            return db.adicionarReserva(reserva)
      }

      fun removerUtilizador(id: Int) : Boolean {
            return db.removerUtilizador(id)
      }

      fun removerPrato(id: Int) : Boolean {
            return db.removerPrato(id)
      }

      fun adicionarPratos(reservaID: Int , pratos: List<Prato>) : Boolean {
            return db.adicionarPratos(reservaID, pratos.toMutableList())
      }

      fun removerPratoAReserva(reservaID: Int, pratos: List<Prato>) : Boolean {
            return db.removerPratoAReserva(reservaID, pratos)
      }

      fun fecharReserva(id: Int) : Double {
            return db.fecharReserva(id)
      }

      fun calcularTotalReserva(id: Int) : Double{
            return db.calcularTotalReserva(id)
      }

      fun checkFiles() {
            db.checkFiles()
      }
}