package dev.Lavyne.mytrackerpal.models

import com.google.gson.annotations.SerializedName

data class ExerciseCategory(
    @SerializedName("category_name") var categoryName:String,
    @SerializedName("category_Id") var categoryId : String
)
