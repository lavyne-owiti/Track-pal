package dev.Lavyne.mytrackerpal.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.Lavyne.mytrackerpal.Repository.ExerciseRepository
import dev.Lavyne.mytrackerpal.models.Exercise
import dev.Lavyne.mytrackerpal.models.ExerciseCategory
import kotlinx.coroutines.launch

class ExerciseViewModel :ViewModel (){
    val exersiceRepository=ExerciseRepository()
    lateinit var exersiceCategoryLiveData : LiveData<List<ExerciseCategory>>
    lateinit var exersiceLIveDate:LiveData<List<Exercise>>
    val errorLiveData= MutableLiveData<String>()
    var selectedexerciseId = mutableListOf<String>()

    fun fetchApiExeriseCategories(accesToken:String){
        viewModelScope.launch {
            val response =exersiceRepository.fetchExerciseCategories(accesToken)
            if (response.isSuccessful){
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun fetchApiExercises(accesToken: String){
        viewModelScope.launch {
            exersiceRepository.fetchApiExercises(accesToken)
        }
    }
    fun getDBExerciseCategories(){
        exersiceCategoryLiveData = exersiceRepository.getDBExercisecategories()
    }
    fun getDBExersice(){
        exersiceLIveDate =exersiceRepository.getDBExercises()
    }
    fun getexercisebyExcategory(categoryId:String){
        exersiceLIveDate =exersiceRepository.getexercisebyCategoryId(categoryId)
    }
}