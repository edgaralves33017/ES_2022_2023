package ui.menu.viewmodel

import data.Repository
import data.model.Mesa
import data.model.Prato
import data.model.Reserva
import data.model.Utilizador

class MenuViewModel {
    private var repository: Repository = Repository()
    fun defaultValues() {
        repository.defaultValues()
    }


}