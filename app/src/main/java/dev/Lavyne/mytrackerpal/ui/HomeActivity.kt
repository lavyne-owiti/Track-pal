package dev.Lavyne.mytrackerpal.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import dev.Lavyne.mytrackerpal.R
import dev.Lavyne.mytrackerpal.ViewModel.ExerciseViewModel
import dev.Lavyne.mytrackerpal.databinding.ActivityHomeBinding
import dev.Lavyne.mytrackerpal.models.LoginResponse
import dev.Lavyne.mytrackerpal.utils.Constants

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var sharedPrefs: SharedPreferences
    lateinit var sharedpref:SharedPreferences
    val exerciseViewModel:ExerciseViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNav()
        exerciseViewModel.getDBExerciseCategories()
        exerciseViewModel.getDBExersice()

        sharedpref=getSharedPreferences(Constants.PREFS_FILE,Context.MODE_PRIVATE)
        binding.imgLogout.setOnClickListener {
            logout()
        }

    }
    fun logout(){
        val editor= sharedpref.edit()
        editor.putString(Constants.ACCESS_TOKEN,Constants.EMPTY_STRING)
        editor.putString(Constants.PROFILE_ID,Constants.EMPTY_STRING)
        editor.putString(Constants.USER_ID,Constants.EMPTY_STRING)
        editor.apply()
//        startActivity(Intent(this,LoginResponse::class.java))
        startActivity(Intent(this,LoginActivity::class.java))
        finish()

    }

    override fun onResume() {
        super.onResume()
        exerciseViewModel.exersiceCategoryLiveData.observe(this, Observer{ exerciseCategories ->
            if (exerciseCategories.isEmpty()){
                exerciseViewModel.fetchApiExeriseCategories(getAccessToken())
            }
        })
        exerciseViewModel.exersiceLIveDate.observe(this, Observer{ exercises->
            if (exercises.isEmpty()){
                exerciseViewModel.fetchApiExercises(getAccessToken())
                  }
        })
        exerciseViewModel.errorLiveData.observe(this, Observer { errorMsg ->
            Toast.makeText(baseContext,errorMsg, Toast.LENGTH_LONG).show()
        })
    }

    fun getAccessToken():String{
        sharedPrefs=getSharedPreferences("WORKOUTLOG_PREFS", MODE_PRIVATE)
        return sharedPrefs.getString("ACCESSTOKEN","")!!
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
    companion object{
        fun getIntent(context:Context):Intent{
            return Intent(context,HomeActivity::class.java)
        }
    }
}
