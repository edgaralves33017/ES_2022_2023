package com.example.es_tablewatcher.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.es_tablewatcher.data.model.Utilizador
import com.example.es_tablewatcher.databinding.ActivityLoginBinding
import com.example.es_tablewatcher.ui.MainActivity
import com.example.es_tablewatcher.ui.login.viewmodel.LoginViewModel


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel : LoginViewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /**
         * Registers this instance of MainActivity so that it can be referenced as the LifeCycleOwner.
         */
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupLayout()
    }

    private fun setupLayout() {
        binding.loginBtn.setOnClickListener {
            val user: Utilizador? = viewModel.login(binding.editUsername.text.toString(), binding.editPassword.text.toString())
            if ( user == null ) {
                Toast.makeText(this, "Nome de utilizador ou password incorrectos!", Toast.LENGTH_SHORT)
            }
            else {
                intent = Intent(this, MainActivity::class.java)
                val bundle = Bundle()
                bundle.putParcelable("user", user)
                startActivity(intent)
                finish()
            }
        }
    }
}