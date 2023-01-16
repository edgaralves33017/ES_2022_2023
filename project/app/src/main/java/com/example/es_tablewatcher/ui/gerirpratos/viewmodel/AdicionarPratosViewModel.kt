package com.example.es_tablewatcher.ui.gerirpratos.viewmodel

import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Prato

class AdicionarPratosViewModel {
    fun obterPratos() : List<Prato> {
        return App.repository.obterPratos();
    }

    fun adicionarPrato(desc: String, preco: String) : Boolean{
        val listPratos = obterPratos()
        val newId = if (listPratos.isEmpty()) 0 else listPratos.last().id+1
        val prato = Prato(newId, desc, preco.toDouble())
        return App.repository.adicionarPrato(prato)
    }
}