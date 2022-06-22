package dev.Lavyne.mytrackerpal

import android.content.Intent
import android.media.MediaCodec
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.Lavyne.mytrackerpal.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding=ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
         listener()

    }
    fun listener(){
        binding.tvLogIn.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }

       binding.btnSignUp.setOnClickListener {
            validateSignUp()
            val intent=Intent(this,HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateSignUp(){
        var firstName=binding.etFirstName.text.toString()
        var lastName=binding.etLastName.text.toString()
        var email=binding.etEmail.text.toString()
        var password=binding.etPassword.text.toString()
        var confirmPassword=binding.etConfirmPassword.text.toString()


//        etEmail.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                tilEmail.error=null
//            }
//
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//            }
//
//            override fun afterTextChanged(s: Editable) {
//
//            }
//        })

        if  (email.isBlank()){
            binding.tilEmail.error ="Email is required"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.tilEmail.error ="email is invalid"
        }
        if  (password.isBlank()){
            binding.tilPassword.error ="password is required"
        }
        if  (confirmPassword.isBlank()){
            binding.tilConfirmPassword.error ="confirm password is required"
        }
        if  (firstName.isBlank()){
            binding.tilFirstName.error ="firstName is required"
        }
        if  (lastName.isBlank()){
            binding.tilLastName.error ="lastName is required"
        }
        if (password != confirmPassword){
            binding.tilConfirmPassword.error="password invalid"
        }

    }
}