package ui.gerirutilizadores.viewmodel

import androidx.recyclerview.widget.RecyclerView
import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Utilizador

class GerirUtilizadoresViewModel {

    fun obterUtilizadores() : List<Utilizador>{
        return App.repository.obterUtilizadores()
    }

    fun removerUtilizador(id: Int) : Boolean {
        return App.repository.removerUtilizador(id)
    }

    fun updateUtilizador(
        utilizadorList: MutableList<Utilizador>,
        utilizador: Utilizador,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
    ) {
        utilizadorList.remove(utilizador)
        removerUtilizador(utilizador.id)
        adapter?.notifyDataSetChanged()
    }
}