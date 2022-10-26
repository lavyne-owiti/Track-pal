package dev.Lavyne.mytrackerpal.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.navigation.NavigationBarView
import dev.Lavyne.mytrackerpal.CategoryAdapter
import dev.Lavyne.mytrackerpal.ExerciseAdapter
import dev.Lavyne.mytrackerpal.R
import dev.Lavyne.mytrackerpal.ViewModel.ExerciseViewModel
import dev.Lavyne.mytrackerpal.databinding.FragmentPlanBinding
import dev.Lavyne.mytrackerpal.databinding.FragmentProfileBinding
import dev.Lavyne.mytrackerpal.models.Exercise
import dev.Lavyne.mytrackerpal.models.ExerciseCategory


class PlanFragment : Fragment() {
    var binding: FragmentPlanBinding ?=null

    val bind get() = binding!!
    val exerciseViewModel: ExerciseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPlanBinding.inflate(inflater,container,false)
        return bind.root
    }

    override fun onResume() {
        super.onResume()
        setupspinners()
        exerciseViewModel.getDBExerciseCategories()
        exerciseViewModel.getDBExersice()
        bind.btnAdditem.setOnClickListener { clickAdditem() }

    }
    fun setupspinners(){
        setupdayspinner()
        setupcategoryspinner()
        setupExerciseSpinner()

    }
    fun setupdayspinner(){
        val daylist= listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
        val dayadapter =ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            daylist
        )
        dayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        bind.spDay.adapter= dayadapter
    }
    fun setupcategoryspinner(){
        exerciseViewModel.exersiceCategoryLiveData.observe(this, Observer { categories ->
            val firstcategory =ExerciseCategory("select category","0")
            val displayAdapter = mutableListOf(firstcategory)
            displayAdapter.addAll(categories)
            val categoryAdapter =CategoryAdapter(requireContext(),displayAdapter)
            bind.spCategory.adapter= categoryAdapter

            bind.spCategory.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val selectedcategory = displayAdapter.get(p2)
                    val categoryid =selectedcategory.categoryId
                    exerciseViewModel.getexercisebyExcategory(categoryid)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        })
    }
    fun setupExerciseSpinner(){
        exerciseViewModel.exersiceLIveDate.observe(this, Observer { exercise->
            val firstexercise =Exercise("0","add a description",
                "add image","0","select exercise")
            val displayAdapter = mutableListOf(firstexercise)
            displayAdapter.addAll(exercise)
            val exerciseAdapter =ExerciseAdapter(requireContext(),exercise)
            bind.spExercise.adapter =exerciseAdapter
        })
    }
    fun clickAdditem(){
        var error=false
        if(bind.spDay.selectedItemPosition==0){
            error = true
            Toast.makeText(requireContext(),"select day",Toast.LENGTH_LONG).show()
        }
        if(bind.spCategory.selectedItemPosition==0){
            error = true
            Toast.makeText(requireContext(),"select category",Toast.LENGTH_LONG).show()
        }
        if(bind.spExercise.selectedItemPosition==0){
            error = true
            Toast.makeText(requireContext(),"select exercise",Toast.LENGTH_LONG).show()
        }
        if (!error){
            val selectedExercise= bind.spExercise.selectedItem as Exercise
            exerciseViewModel.selectedexerciseId.add(selectedExercise.exerciseId)
            val day= bind.spDay.selectedItem.toString()
            bind.spExercise.setSelection(0)
            bind.spCategory.setSelection(0)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}