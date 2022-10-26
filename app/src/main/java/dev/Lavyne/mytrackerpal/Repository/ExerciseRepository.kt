package dev.Lavyne.mytrackerpal.Repository

import androidx.lifecycle.LiveData
import dev.Lavyne.mytrackerpal.API.ApiClient
import dev.Lavyne.mytrackerpal.API.ApiInterface
import dev.Lavyne.mytrackerpal.MyTrackPal
import dev.Lavyne.mytrackerpal.database.ExerciseCategoryDAO
import dev.Lavyne.mytrackerpal.database.WorkoutDB
import dev.Lavyne.mytrackerpal.models.Exercise
import dev.Lavyne.mytrackerpal.models.ExerciseCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ExerciseRepository {
    val apiClient= ApiClient.buldApiClient(ApiInterface::class.java)
    val database = WorkoutDB.getDatabase(MyTrackPal.appContext)
    val exerciseCategoryDAO =database.exerciseCategoryDAO()
    val exerciseDAO=database.exerciseDAO()

    suspend fun fetchExerciseCategories(accessToken:String)
    = withContext(Dispatchers.IO){
       val response = apiClient.exerciseCategory(accessToken)
        if (response.isSuccessful){
            val exerciseCategories=response.body()
            exerciseCategories?.forEach{ category ->
                exerciseCategoryDAO.insertexerciseCategory(category)
            }
        }
        return@withContext response
    }

    suspend fun fetchApiExercises(accessToken: String){
        withContext(Dispatchers.IO){
            val response =apiClient.fetchExercise(accessToken)
            if (response.isSuccessful){
                val exercise =response.body()
                exercise?.forEach { exercise ->
                    exerciseDAO.insertExercise(exercise)
                }            }
        }
    }
    fun getDBExercisecategories():LiveData<List<ExerciseCategory>>{
        return exerciseCategoryDAO.getExecrciseCategories()

    }
    fun getDBExercises():LiveData<List<Exercise>>{
        return exerciseDAO.getExercise()
    }
    fun getexercisebyCategoryId(categoryId:String):LiveData<List<Exercise>>{
        return  exerciseDAO.getExercisecategory(categoryId)

    }
}