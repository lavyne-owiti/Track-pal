package dev.Lavyne.mytrackerpal.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.Lavyne.mytrackerpal.Repository.ExerciseRepository
import dev.Lavyne.mytrackerpal.models.ExerciseCategory
import kotlinx.coroutines.launch

class ExerciseViewModel :ViewModel (){
    val exersiceRepository=ExerciseRepository()
    val exersiceCategoryLiveData=MutableLiveData<List<ExerciseCategory>>()
    val errorLiveData= MutableLiveData<String>()

    fun fetchExeriseCategories(accesToken:String){
        viewModelScope.launch {
            val response =exersiceRepository.fetchExerciseCategories(accesToken)
            if (response.isSuccessful){
                exersiceCategoryLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}