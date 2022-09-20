package dev.Lavyne.mytrackerpal.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.Lavyne.mytrackerpal.R
import dev.Lavyne.mytrackerpal.ViewModel.ExerciseViewModel
import dev.Lavyne.mytrackerpal.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
        triggerFetchExerciseCtegories()
    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exersiceCategoryLiveData.observe(this, Observer{ categoryResponse ->
            Toast.makeText(this,"fetched ${categoryResponse.size} category",Toast.LENGTH_LONG).show()
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg ->
            Toast.makeText(baseContext,errorMsg, Toast.LENGTH_LONG).show()
        })
    }

    fun triggerFetchExerciseCtegories(){
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        var accesstoken=sharedPrefs.getString("ACCESSTOKEN","")
        exerciseViewModel.fetchExeriseCategories(accesstoken!!)
    }

    fun setupBottomNav(){
        binding.bottomNav.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome, HomeFragment()).commit()
                    true
                }
                R.id.plan ->{
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fcvHome,
                            PlanFragment()
                        ).commit()
                        true
                }
                R.id.track ->{
                        supportFragmentManager.beginTransaction().replace(
                            R.id.fcvHome,
                            TrackFragment()
                        ).commit()
                        true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fcvHome,
                        ProfileFragment()
                    ).commit()
                    true
                }
                else->false
            }
        }

    }
}
