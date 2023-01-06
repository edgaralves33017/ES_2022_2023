package ui.gerirutilizadores.viewmodel

import data.Repository
import data.model.Utilizador

class GerirUtilizadoresViewModel {

    private var repository: Repository = Repository()

    fun obterUtilizadores() : List<Utilizador>{
        return repository.obterUtilizadores()
    }

    fun adicionarUtilizador(utilizador: Utilizador) : Boolean{
        return repository.adicionarUtilizador(utilizador)
    }

    fun removerUtilizador(id: Int) : Boolean {
        return repository.removerUtilizador(id)
    }
}