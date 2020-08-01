package com.example.cyberfables

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cyberfables.database.AppDatabase
import com.example.cyberfables.database.FableInit
import com.example.cyberfables.entities.Fable

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(applicationContext)
        db.fableDao().insertFables(FableInit().initFables())

        //init mediaPlayer


        //set the app to fullscreen (hide status bar)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Setup nav controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }


}
