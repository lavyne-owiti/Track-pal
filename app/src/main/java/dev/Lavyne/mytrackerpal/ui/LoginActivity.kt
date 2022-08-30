package dev.Lavyne.mytrackerpal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.Lavyne.mytrackerpal.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding:ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleListener()

    }
    fun handleListener(){
        binding.tvSignUp.setOnClickListener {
            val intent=Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogIn.setOnClickListener {
            val intent=Intent(this, HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
        }
    }
    fun validateLogin(){
        var email =binding.etEmail.text.toString()
        var password =binding.etEmail.text.toString()
        var error =false
        if  (email.isBlank()){
            error=true
            binding.tilEmail.error ="Email is required"
        }
        if  (password.isBlank()){
            error=true
            binding.tilPassword.error ="password is required"
        }
        if (!error){

        }
    }

}