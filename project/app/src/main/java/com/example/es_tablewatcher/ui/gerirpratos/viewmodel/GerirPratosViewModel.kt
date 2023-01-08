package com.example.es_tablewatcher.ui.gerirpratos.viewmodel

import com.example.es_tablewatcher.data.Repository
import com.example.es_tablewatcher.data.model.Prato

class GerirPratosViewModel {

    private var repository: Repository = Repository()

    fun obterPratos() : List<Prato> {
        return repository.obterPratos();
    }

    fun adicionarPrato(desc: String, preco: String) : Boolean{
        val listPratos = obterPratos()
        val newId = if (listPratos.isEmpty()) 0 else listPratos.last().id+1
        val prato = Prato(newId, desc, preco.toDouble())
        return repository.adicionarPrato(prato)
    }

    fun removerPrato(id: Int) : Boolean {
        return repository.removerPrato(id)
    }
}