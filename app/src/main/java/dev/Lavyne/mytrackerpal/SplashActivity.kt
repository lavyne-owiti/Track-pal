package dev.Lavyne.mytrackerpal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.SplashActivity)

        val intent =Intent(this,LoginActivity::class.java)
        startActivity(intent)
    }

}
