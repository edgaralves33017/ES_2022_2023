package com.example.es_tablewatcher.ui.gerirpratos.viewmodel

import androidx.recyclerview.widget.RecyclerView
import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Prato

class GerirPratosViewModel {

    fun obterPratos() : List<Prato> {
        return App.repository.obterPratos();
    }

    fun removerPrato(id: Int) : Boolean {
        return App.repository.removerPrato(id)
    }

    fun updatePrato(
        pratoList: MutableList<Prato>,
        prato: Prato,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
    ) {
        pratoList.remove(prato)
        removerPrato(prato.id)
        adapter?.notifyDataSetChanged()
    }
}