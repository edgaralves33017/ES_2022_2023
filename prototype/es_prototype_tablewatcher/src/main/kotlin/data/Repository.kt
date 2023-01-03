package data

import data.local.LocalRepository
import data.model.Mesa
import data.model.Prato
import data.model.Reserva
import data.model.Utilizador


class Repository {
      private var db : LocalRepository = LocalRepository()

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

      fun adicionarPratos(reserva: Reserva, pratos: List<Prato>) : Boolean {
            return db.adicionarPratos(reserva, pratos)
      }

      fun removerPratoAReserva(reserva: Reserva, prato: Prato) : Boolean {
            return db.removerPratoAReserva(reserva, prato)
      }

      fun fecharReserva(reserva: Reserva) : Double {
            return db.fecharReserva(reserva)
      }
}