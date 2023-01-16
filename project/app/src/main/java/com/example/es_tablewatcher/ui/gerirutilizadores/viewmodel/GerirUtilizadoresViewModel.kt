package ui.gerirutilizadores.viewmodel

import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Utilizador

class GerirUtilizadoresViewModel {

    fun obterUtilizadores() : List<Utilizador>{
        return App.repository.obterUtilizadores()
    }

    fun adicionarUtilizador(username: String, password: String, isAdmin: Boolean) : Boolean{
        val user = Utilizador(obterUtilizadores().last().id+1, username, password, isAdmin)
        return App.repository.adicionarUtilizador(user)
    }

    fun removerUtilizador(id: Int) : Boolean {
        return App.repository.removerUtilizador(id)
    }
}