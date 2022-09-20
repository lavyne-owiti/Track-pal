package dev.Lavyne.mytrackerpal.Repository

import dev.Lavyne.mytrackerpal.API.ApiClient
import dev.Lavyne.mytrackerpal.API.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient= ApiClient.buldApiClient(ApiInterface::class.java)

    suspend fun fetchExerciseCategories(accessToken:String)
    = withContext(Dispatchers.IO){
        return@withContext apiClient.fetchExerciseCategories(accessToken)
    }
}