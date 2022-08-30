package dev.Lavyne.mytrackerpal

import dev.Lavyne.mytrackerpal.models.RegisterRequest
import dev.Lavyne.mytrackerpal.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
}