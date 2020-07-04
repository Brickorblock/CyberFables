package com.example.cyberfables

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cyberfables.entities.Fable

class MainActivity : AppCompatActivity(),
    BookshelfFragment.OnBookSelected {

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //TODO implement fragment switching to book when selected in BookDetail
        //attach BookshelfFragment
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.main_root, BookshelfFragment.newInstance(), "BookshelfFragment")
                .commit()
        }

    }
    //TODO implement proper fragment messaging if needed
    override fun onBookSelected(fable: Fable) {
        Toast.makeText(this, "Hey, you selected " + fable.title + "!",
            Toast.LENGTH_SHORT).show()
    }

    fun initFables(): ArrayList<Fable> {
        Log.d(TAG, "initFables Called")
        var fables: ArrayList<Fable> = ArrayList()

        val fable1 = Fable(
            "Kizumonogatari",
            "[TEST 1] This prequel novel features the life of Koyomi Araragi before the events in Hitagi Crab. It details Araragi's encounter with the vampire Kiss-shot Acerola-orion Heart-under-blade and his journey back into humanity from being a vampire.",
            R.drawable.test_cover1,
            R.drawable.test_icon1,
            arrayListOf(R.drawable.test_cover1)
        )

        val fable2 = Fable(
            "Nisemonogatari",
            "[TEST 2] Nisemonogatari serves as a direct sequel to Bakemonogatari, following the story of Koyomi Araragi as he continues his relationships with the characters from the series. The two-part novel focuses on supernatural events that involve Koyomi's two younger sisters...",
            R.drawable.test_cover2,
            R.drawable.test_icon2,
            arrayListOf(R.drawable.test_cover2)
        )

        fables.add(fable1)
        fables.add(fable2)

        return fables
    }

}
