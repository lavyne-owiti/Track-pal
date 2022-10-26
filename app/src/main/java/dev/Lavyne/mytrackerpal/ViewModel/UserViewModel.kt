package dev.Lavyne.mytrackerpal.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.Lavyne.mytrackerpal.Repository.UserRepository
import dev.Lavyne.mytrackerpal.models.*
import kotlinx.coroutines.launch

class UserViewModel :ViewModel() {
    val userRepository=UserRepository()
    val loginLivedata=MutableLiveData<LoginResponse>()
    val registerLivedata =MutableLiveData<RegisterResponse>()
    val loginerror=MutableLiveData<String>()
    val registererror=MutableLiveData<String>()
    var profileResponseLiveData=MutableLiveData<ProfileResponse>()
    val profileErrorLiveData=MutableLiveData<String?>()

    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response =userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginLivedata.postValue(response.body())
            }
            else{
                loginerror.postValue(response.errorBody()?.string())
            }
        }
    }
    fun register(registerRequest: RegisterRequest){
        viewModelScope.launch {
            val response =userRepository.registerUser(registerRequest)
            if (response.isSuccessful){
                registerLivedata.postValue(response.body())
            }
            else{
                registererror.postValue(response.errorBody()?.string())
            }
        }
    }
    fun profileUser(profileRequest: ProfileRequest){
        viewModelScope.launch {
            val response=userRepository.profileUser(profileRequest)
            if (response.isSuccessful){
                profileResponseLiveData.postValue(response.body())

            }else{
                val error=response.errorBody()?.string()
                profileErrorLiveData.postValue(error)
            }
        }

    }


}