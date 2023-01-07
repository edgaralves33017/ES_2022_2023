package ui.gerirutilizadores.viewmodel

import data.Repository
import data.model.Utilizador

class GerirUtilizadoresViewModel {

    private var repository: Repository = Repository()

    fun obterUtilizadores() : List<Utilizador>{
        return repository.obterUtilizadores()
    }

    fun adicionarUtilizador(username: String, password: String, isAdmin: Boolean) : Boolean{
        val user = Utilizador(obterUtilizadores().last().id+1, username, password, isAdmin)
        return repository.adicionarUtilizador(user)
    }

    fun removerUtilizador(id: Int) : Boolean {
        return repository.removerUtilizador(id)
    }
}