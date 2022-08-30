package dev.Lavyne.mytrackerpal.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dev.Lavyne.mytrackerpal.ApiClient
import dev.Lavyne.mytrackerpal.ApiInterface
import dev.Lavyne.mytrackerpal.databinding.ActivitySignUpBinding
import dev.Lavyne.mytrackerpal.models.RegisterRequest
import dev.Lavyne.mytrackerpal.models.RegisterResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        listener()


    }

    fun listener() {
        binding.tvLogIn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            validateSignUp()
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateSignUp() {
        var firstName = binding.etFirstName.text.toString()
        var lastName = binding.etLastName.text.toString()
        var email = binding.etEmail.text.toString()
        var password = binding.etPassword.text.toString()
        var confirmPassword = binding.etConfirmPassword.text.toString()
        var phoneNumber = binding.etPhone.text.toString()
        var error = false
//        etEmail.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//                tilEmail.error=null
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
//            override fun afterTextChanged(s: Editable) { }
//        })

        if (email.isBlank()) {
            error = true
            binding.tilEmail.error = "Email is required"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            error = true
            binding.tilEmail.error = "email is invalid"
        }
        if (phoneNumber.isBlank()) {
            error = true
            binding.tilPhoneNumber.error = "phone number is required"
        }
        if (password.isBlank()) {
            error = true
            binding.tilPassword.error = "password is required"
        }
        if (confirmPassword.isBlank()) {
            error = true
            binding.tilConfirmPassword.error = "confirm password is required"
        }
        if (firstName.isBlank()) {
            error = true
            binding.tilFirstName.error = "firstName is required"
        }
        if (lastName.isBlank()) {
            error = true
            binding.tilLastName.error = "lastName is required"
        }
        if (password != confirmPassword) {
            error = true
            binding.tilConfirmPassword.error = "password invalid"
        }
        if (!error) {
            binding.pvRegister.visibility=View.VISIBLE
            var registerRequest = RegisterRequest(firstName, lastName, phoneNumber, email, password)
            makeRegistrationRequest(registerRequest)
        }
    }

    fun makeRegistrationRequest(requestRequest: RegisterRequest) {
        var apiCLient = ApiClient.buldApiClient(ApiInterface::class.java)
        var request = apiCLient.registerUser(requestRequest)

        request.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(

                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                binding.pvRegister.visibility=View.GONE
                if (response.isSuccessful) {
                    var message = response.body()?.message
                    Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(baseContext, LoginActivity::class.java))
                } else {
                    var error = response.errorBody()?.string()
                    Toast.makeText(baseContext, error, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}