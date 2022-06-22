package dev.Lavyne.mytrackerpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
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
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.btnLogIn.setOnClickListener {
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
            validateLogin()
        }
    }
    fun validateLogin(){
        var email =binding.etEmail.text.toString()
        var password =binding.etEmail.text.toString()
        if  (email.isBlank()){
            binding.tilEmail.error ="Email is required"
        }
        if  (password.isBlank()){
            binding.tilPassword.error ="password is required"
        }
    }

}