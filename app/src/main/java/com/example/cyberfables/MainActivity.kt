package com.example.cyberfables

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cyberfables.database.AppDatabase
import com.example.cyberfables.entities.Fable

class MainActivity : AppCompatActivity(),
    BookshelfFragment.OnBookSelected {

    private val TAG = "MainActivity"
    lateinit var navController: NavController
    var fables : ArrayList<Fable> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(applicationContext)
        db.fableDao().insertFables(initFables())

        //Setup nav controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }

    //TODO implement proper fragment messaging if needed
    override fun onBookSelected(fable: Fable) {
        Toast.makeText(this, "Hey, you selected " + fable.title + "!",
            Toast.LENGTH_SHORT).show()
    }

    fun initFables(): ArrayList<Fable> {
        Log.d(TAG, "initFables Called")

        val fable1 = Fable(
            0,
            "Kizumonogatari",
            "[TEST 1] This prequel novel features the life of Koyomi Araragi before the events in Hitagi Crab. It details Araragi's encounter with the vampire Kiss-shot Acerola-orion Heart-under-blade and his journey back into humanity from being a vampire.",
            R.drawable.test_cover1,
            R.drawable.test_icon1,
            arrayListOf(
                R.drawable.littlered_1,
                R.drawable.littlered_2,
                R.drawable.littlered_3,
                R.drawable.littlered_3_decision1,
                R.drawable.littlered_4,
                R.drawable.littlered_5,
                R.drawable.littlered_6,
                R.drawable.littlered_6_decision2,
                R.drawable.littlered_7
            ),
            listOf(R.drawable.littlered_3_decision1),
            listOf(R.id.action_readerFragment_to_littleredInteractive1Fragment)
        )

        val fable2 = Fable(
            1,
            "Nisemonogatari",
            "[TEST 2] Nisemonogatari serves as a direct sequel to Bakemonogatari, following the story of Koyomi Araragi as he continues his relationships with the characters from the series. The two-part novel focuses on supernatural events that involve Koyomi's two younger sisters...",
            R.drawable.test_cover2,
            R.drawable.test_icon2,
            arrayListOf(R.drawable.test_cover2),
            listOf(), listOf()
        )

        fables.add(fable1)
        fables.add(fable2)

        return fables
    }

}
