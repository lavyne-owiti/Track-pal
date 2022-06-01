package dev.Lavyne.mytrackerpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    lateinit var tvSignUp:TextView
    lateinit var etEmail:TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var btnLogIn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        tvSignUp =findViewById(R.id.tvSignUp)
        etEmail=findViewById(R.id.etEmail)
        etPassword=findViewById(R.id.etPassword)
        btnLogIn=findViewById(R.id.btnLogIn)
        tilEmail=findViewById(R.id.tilEmail)
        tilPassword=findViewById(R.id.tilPassword)

        tvSignUp.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        btnLogIn.setOnClickListener {
            validateLogin()
        }

    }
    fun validateLogin(){
        var email =etEmail.text.toString()
        var password =etEmail.text.toString()
        if  (email.isBlank()){
            tilEmail.error ="Email is required"
        }
        if  (password.isBlank()){
            tilPassword.error ="password is required"
        }
    }

}