package ui.login.viewmodel

import data.Repository
import data.model.Utilizador

class LoginViewModel {
    private var repository: Repository = Repository()

    fun login (username: String, password: String) : Utilizador?{
        return repository.login(username, password)
    }
}