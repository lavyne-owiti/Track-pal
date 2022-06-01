package dev.Lavyne.mytrackerpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class SignUpActivity : AppCompatActivity() {
    lateinit var tilFirstName:TextInputLayout
    lateinit var tilLastName:TextInputLayout
    lateinit var tilEmail:TextInputLayout
    lateinit var tilPassword:TextInputLayout
    lateinit var tilConfirmPassword:TextInputLayout
    lateinit var etFirstName:TextInputEditText
    lateinit var etLastName:TextInputEditText
    lateinit var etEmail:TextInputEditText
    lateinit var etPassword:TextInputEditText
    lateinit var etConfirmPassword:TextInputEditText
    lateinit var tvLogIn:TextView
    lateinit var btnSignUp:Button
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
            tilConfirmPassword=findViewById(R.id.tilConfirmPassword)
            tilPassword=findViewById(R.id.tilPassword)
            tilFirstName=findViewById(R.id.tilFirstName)
            tilLastName=findViewById(R.id.tilLastName)
            tilEmail=findViewById(R.id.tilEmail)
            etConfirmPassword=findViewById(R.id.etConfirmPassword)
            etEmail=findViewById(R.id.etEmail)
            etFirstName=findViewById(R.id.etFirstName)
            etLastName=findViewById(R.id.etLastName)
            etPassword=findViewById(R.id.etPassword)
            tvLogIn=findViewById(R.id.tvLogIn)
            btnSignUp=findViewById(R.id.btnSignUp)

            tvLogIn.setOnClickListener {
                val intent=Intent(this,LoginActivity::class.java)
                startActivity(intent)
            }

            btnSignUp.setOnClickListener {
                validateSignUp()
            }

    }
    fun validateSignUp(){
        var firstName=etFirstName.text.toString()
        var lastName=etLastName.text.toString()
        var email=etEmail.text.toString()
        var password=etPassword.text.toString()
        var confirmPassword=etConfirmPassword.text.toString()
        if  (email.isBlank()){
            tilEmail.error ="Email is required"
        }
        if  (password.isBlank()){
            tilPassword.error ="password is required"
        }
        if  (confirmPassword.isBlank()){
            tilConfirmPassword.error ="confirm password is required"
        }
        if  (firstName.isBlank()){
            tilFirstName.error ="firstName is required"
        }
        if  (lastName.isBlank()){
            tilLastName.error ="lastName is required"
        }

    }
}