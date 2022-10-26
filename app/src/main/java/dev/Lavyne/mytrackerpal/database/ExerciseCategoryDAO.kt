package dev.Lavyne.mytrackerpal.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.Lavyne.mytrackerpal.models.ExerciseCategory

@Dao
interface ExerciseCategoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertexerciseCategory(exerciseCategory: ExerciseCategory)

    @Query("SELECT * FROM exerciseCategory")
    fun getExecrciseCategories():LiveData<List<ExerciseCategory>>
}