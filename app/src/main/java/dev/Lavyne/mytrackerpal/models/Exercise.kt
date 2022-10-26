package dev.Lavyne.mytrackerpal.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Exercise")
data class Exercise(
    @PrimaryKey @SerializedName("exersice_id") var exerciseId:String,
    var description:String,
    var image:String,
    @SerializedName("category_id") var categoryId:String,
    @SerializedName("exersice_name") var exerciseName:String

)
