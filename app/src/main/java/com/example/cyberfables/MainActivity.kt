package com.example.cyberfables

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.cyberfables.database.AppDatabase
import com.example.cyberfables.entities.Fable

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var navController: NavController
    var fables : ArrayList<Fable> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = AppDatabase.getDatabase(applicationContext)
        db.fableDao().insertFables(initFables())

        //set the app to fullscreen (hide status bar)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        //Setup nav controller
        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

    }

    fun initFables(): ArrayList<Fable> {
        Log.d(TAG, "initFables Called")

        val fable1 = Fable(
            0,
            "Safe & Secure Wins the Race!",
            "Join the Tortoise and his best friend the Hare as they discover the challenges of owning a social media account by learning the importance of a strong password.",
            "Passwords, Online Security",
            R.drawable.fable1_cover,
            R.drawable.fable1_icon,
            arrayListOf(
                R.drawable.tortoise_1,
                R.drawable.tortoise_2,
                R.drawable.tortoise_3,
                R.drawable.tortoise_4,
                R.drawable.tortoise_5,
                R.drawable.tortoise_6,
                R.drawable.tortoise_7,
                R.drawable.tortoise_8,
                R.drawable.tortoise_10,
                R.drawable.tortoise_11,
                R.drawable.tortoise_12,
                R.drawable.tortoise_13,
                R.drawable.tortoise_1
            ),
            //empty lists = no interactive content
            mutableListOf(R.drawable.tortoise_13),
            mutableListOf(R.id.action_readerFragment_to_tortoiseInteractiveFragment)
        )

        val fable2 = Fable(
            1,
            "Little Red & the Cyber Wolf",
            "Follow Little Red Riding Hood’s journey into owning her first Facebook account, where she is tricked into trusting someone who isn't who they say they are...",
            "Phishing, Digital Stranger Danger",
            R.drawable.fable2_cover,
            R.drawable.fable2_icon,
            // this is the first "chapter" - up to the first interactive fragment
            // note - must include one page after the decision due to how the adapter handles
            // interactive checks (looks at prev and curr pages)
            arrayListOf(
                R.drawable.littlered_1,
                R.drawable.littlered_2,
                R.drawable.littlered_3,
                R.drawable.littlered_3_decision1,
                R.drawable.littlered_4,
                R.drawable.littlered_5,
                R.drawable.littlered_6,
                R.drawable.littlered_6_decision2,
                R.drawable.littlered_7,
                R.drawable.littlered_8_badend,
                R.drawable.littlered_8_goodend,
                R.drawable.littlered_9,
                R.drawable.littlered_10_badend,
                R.drawable.littlered_10_goodend,
                R.drawable.littlered_11_converged,
                R.drawable.littlered_12_converged
                ),
            mutableListOf(R.drawable.littlered_3_decision1, R.drawable.littlered_6_decision2),
            mutableListOf(
                R.id.action_readerFragment_to_littleredInteractive1Fragment,
                R.id.action_readerFragment_to_littleredInteractive2Fragment)
        )

        val fable3 = Fable(
            2,
            "Hansel & Gretel's Digital Footprint",
            "Explore a magical forest called \"The Internet\" with Hansel & Gretel, where they learn how everything they do online leaves a footprint that anyone can follow.",
            "Digital Footprints, Digital Privacy",
            R.drawable.fable3_cover,
            R.drawable.fable3_icon,
            arrayListOf(
                R.drawable.hansel_1,
                R.drawable.hansel_2,
                R.drawable.hansel_3,
                R.drawable.hansel_4,
                R.drawable.hansel_5,
                R.drawable.hansel_6,
                R.drawable.hansel_7,
                R.drawable.hansel_8,
                R.drawable.hansel_9,
                R.drawable.hansel_10,
                R.drawable.hansel_11,
                R.drawable.hansel_12,
                R.drawable.hansel_13,
                R.drawable.hansel_14,
                R.drawable.hansel_15,
                R.drawable.hansel_16,
                R.drawable.hansel_17,
                R.drawable.hansel_18,
                R.drawable.hansel_19,
                R.drawable.hansel_20,
                R.drawable.hansel_21,
                R.drawable.hansel_22,
                R.drawable.hansel_23
            ),
            //empty lists = no interactive content
            mutableListOf(), mutableListOf()
        )

        fables.add(fable1)
        fables.add(fable2)
        fables.add(fable3)

        return fables
    }

}
