package dev.Lavyne.mytrackerpal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.Lavyne.mytrackerpal.models.Exercise
import dev.Lavyne.mytrackerpal.models.ExerciseCategory

class ExerciseAdapter(context : Context, var exercises:List<Exercise>):

    ArrayAdapter<Exercise>(context,0,exercises) {

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return getcustomview(position, convertView, parent)
        }

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            return getcustomview(position,convertView,parent)
        }

        fun getcustomview(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = LayoutInflater.from(context).inflate(R.layout.customspinner_exercise,parent,false)
            val tvSpinnerText =view.findViewById<TextView>(R.id.tvExercieSpinner)
            tvSpinnerText.text=exercises.get(position).exerciseName
            return view

        }
}