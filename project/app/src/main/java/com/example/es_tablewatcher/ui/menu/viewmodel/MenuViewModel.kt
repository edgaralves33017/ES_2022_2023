package com.example.es_tablewatcher.ui.menu.viewmodel

import com.example.es_tablewatcher.data.Repository

class MenuViewModel {
    private var repository: Repository = Repository()
    fun defaultValues() {
        repository.defaultValues()
    }


}