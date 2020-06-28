package com.example.cyberfables

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //attach BookshelfFragment
        val fragManager = supportFragmentManager
        val fragTransaction = fragManager.beginTransaction()

        val fragment = BookshelfFragment()
        fragTransaction.add(R.id.main_root, fragment).commit()

        Log.d(TAG, "test2")
    }
}
