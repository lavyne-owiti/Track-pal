package dev.Lavyne.mytrackerpal.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.Lavyne.mytrackerpal.models.Exercise
import dev.Lavyne.mytrackerpal.models.ExerciseCategory

@Database(entities = arrayOf(ExerciseCategory::class,Exercise::class), version = 2)
abstract class WorkoutDB :RoomDatabase(){
    abstract fun exerciseCategoryDAO():ExerciseCategoryDAO

    abstract fun exerciseDAO():ExerciseDAO

    companion object{
        private var database:WorkoutDB?=null
        fun getDatabase (context:Context):WorkoutDB{
            if (database==null){
                database= Room.databaseBuilder(context,WorkoutDB::class.java,"WorkoutDB")
                    .fallbackToDestructiveMigrationFrom()
                    .build()
            }
            return database as WorkoutDB
        }
    }
}