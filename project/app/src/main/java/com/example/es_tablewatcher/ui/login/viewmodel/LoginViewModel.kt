package com.example.es_tablewatcher.ui.login.viewmodel

import com.example.es_tablewatcher.data.Repository
import com.example.es_tablewatcher.data.model.Utilizador

class LoginViewModel {
    private var repository: Repository = Repository()

    fun login (username: String, password: String) : Utilizador?{
        return repository.login(username, password)
    }
}