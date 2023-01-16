package com.example.es_tablewatcher.ui.login.viewmodel

import com.example.es_tablewatcher.App
import com.example.es_tablewatcher.data.Repository
import com.example.es_tablewatcher.data.model.Utilizador

class LoginViewModel {
    fun login (username: String, password: String) : Utilizador?{
        return App.repository.login(username, password)
    }
}