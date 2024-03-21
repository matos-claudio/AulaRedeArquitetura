package com.claudio.aularedearquitetura.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.claudio.aularedearquitetura.databinding.ActivityLoginBinding
import com.claudio.aularedearquitetura.ui.movie.MovieListActivity


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btLogin.setOnClickListener {
            val username = binding.edtUser.text.toString().trim()
            val password = binding.edtPass.text.toString().trim()
            loginViewModel.perfomLogin(username, password)
        }

        loginViewModel.loginResultLiveData.observe(this, Observer { isSuccessful ->
            if(isSuccessful) {
                val intent = Intent(this, MovieListActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario ou senha inválidos", Toast.LENGTH_LONG).show()
            }
        })
    }
}