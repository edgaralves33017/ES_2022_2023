package com.example.es_tablewatcher.ui.gerirpratos.viewmodel

import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.model.Prato

class GerirPratosViewModel {

    fun obterPratos() : List<Prato> {
        return App.repository.obterPratos();
    }

    fun removerPrato(id: Int) : Boolean {
        return App.repository.removerPrato(id)
    }
}