package dev.Lavyne.mytrackerpal

import android.app.Application
import android.content.Context

class MyTrackPal:Application() {
    companion object{
        lateinit var appContext:Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext =applicationContext
    }
}