package com.example.cyberfables.interactives

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.*


class LittleredInteractive1Fragment : Fragment() {

    companion object {
        val KEY = "LittleredInteractive1Fragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_littlered_interactive1, container, false)

        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handles custom hitbox touch zones on a static image.
        // See Helper.getHitboxColour() for more details
        hitboxes.setOnTouchListener(OnTouchListener { v, ev ->
            val action = ev.action
            val evX = ev.getX().toInt()
            val evY = ev.getY().toInt()

            Log.d("LittleredInteractive", "evX = $evX, evY = $evY")

            if (action == MotionEvent.ACTION_UP) {
                val pixelColour: Int = Helper().getHitboxColour(hitboxes, evX, evY)
                Log.d("LittleredInteractive", "pixelColour = $pixelColour")

                determineChoice(pixelColour)
            }

            true
        })
    }

    fun determineChoice(pixelColour: Int) {
        var choiceMade = false
        lateinit var choiceBundle: Bundle

        when (pixelColour) {
            Color.WHITE -> Toast.makeText(
                context,
                "Tap on the profile you'd like to add!",
                Toast.LENGTH_SHORT
            ).show()

            // correct choice
            Color.BLUE -> {
                choiceBundle = bundleOf(KEY to true)
                choiceMade = true
            }

            // incorrect choice
            Color.RED -> {
                choiceBundle = bundleOf(KEY to false)
                choiceMade = true
            }
        }

        //navigate to results based on choice
        if (choiceMade) {
            Log.d("LittleredInteractive", "bundle = $choiceBundle")

            (context as MainActivity).navController.navigate(
                R.id.action_littleredInteractive1Fragment_to_littleredResult1Fragment,
                choiceBundle
            )
        }
    }

}

