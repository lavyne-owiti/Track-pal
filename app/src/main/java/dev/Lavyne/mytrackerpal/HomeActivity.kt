package dev.Lavyne.mytrackerpal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.Lavyne.mytrackerpal.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var bnvHome:BottomNavigationView
    lateinit var fcvHome:FragmentContainerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        castView()
        setupBottomNav()
    }
    fun castView(){
        bnvHome=findViewById(R.id.bottom_Nav)
        fcvHome=findViewById(R.id.fcvHome)

    }
    fun setupBottomNav(){
        bnvHome.setOnItemSelectedListener { item->
            when(item.itemId){
                R.id.home ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,HomeFragment()).commit()
                    true
                }
                R.id.plan ->{
                        supportFragmentManager.beginTransaction().replace(R.id.fcvHome,PlanFragment()).commit()
                        true
                }
                R.id.track ->{
                        supportFragmentManager.beginTransaction().replace(R.id.fcvHome,TrackFragment()).commit()
                        true
                }
                R.id.profile ->{
                    supportFragmentManager.beginTransaction().replace(R.id.fcvHome,ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }

    }
}
