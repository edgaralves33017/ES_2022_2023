package ui.gerirpratos.viewmodel

import data.Repository
import data.model.Prato

class GerirPratosViewModel {

    private var repository: Repository = Repository()

    fun obterPratos() : List<Prato> {
        return repository.obterPratos();
    }

    fun adicionarPrato(prato:Prato) : Boolean{
        return repository.adicionarPrato(prato)
    }

    fun removerPrato(id: Int) : Boolean {
        return repository.removerPrato(id)
    }
}