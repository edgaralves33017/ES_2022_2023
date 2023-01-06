package ui.login

import ui.login.viewmodel.LoginViewModel
import ui.menu.Menu

class Login {
    private val viewModel = LoginViewModel()
    init {
        println("######################################")
        println("Por favor introduza o seu nome de utilizador e password: ")
        print("Nome de utilizador: ")
        val username = readln()
        print("Password: ")
        val password = readln()

        val user = viewModel.login(username, password)
        if (user == null) {
            println("Nome de utilizador ou password errados!")
            Login()
        }
        else {
            println("\n######################################")
            println ("Bem vindo, ${user.username}!")
            println("######################################\n")
            Menu(user)
        }
    }
}