package dev.Lavyne.mytrackerpal.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ExerciseCategory")
data class ExerciseCategory(
    @SerializedName("category_name") var categoryName:String,
    @PrimaryKey @SerializedName("category_Id") var categoryId : String
)
