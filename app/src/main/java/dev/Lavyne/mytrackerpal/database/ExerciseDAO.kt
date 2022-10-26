package dev.Lavyne.mytrackerpal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.Lavyne.mytrackerpal.models.Exercise

@Dao
interface ExerciseDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun  insertExercise(exercise:Exercise)

    @Query("SELECT * FROM Exercise")
    fun getExercise():LiveData<List<Exercise>>


    @Query("SELECT *FROM Exercise WHERE categoryId=:categoryId")
    fun getExercisecategory (categoryId:String):LiveData<List<Exercise>>
}
