package dev.Lavyne.mytrackerpal.API

import dev.Lavyne.mytrackerpal.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    suspend fun registerUser(@Body registerRequest: RegisterRequest):Response<RegisterResponse>
    @POST("/login")
    suspend fun loginUser(@Body loginRequest: LoginRequest):Response<LoginResponse>
    @GET("/exersiceCategory")
    suspend fun exerciseCategory(@Header("Authorization")token:String):Response<List<ExerciseCategory>>
}