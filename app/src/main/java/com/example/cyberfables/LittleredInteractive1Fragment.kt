package com.example.cyberfables

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
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.*


class LittleredInteractive1Fragment : Fragment() {


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

                when (pixelColour) {
                    Color.WHITE -> Toast.makeText(
                        context,
                        "Tap on the profile you'd like to add!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // correct choice
                    Color.BLUE -> {
                        //todo
                        Toast.makeText(context, "correct!", Toast.LENGTH_SHORT).show()
                    }

                    // incorrect choice
                    Color.RED -> {
                        //todo
                        Toast.makeText(context, "incorrect!", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            true
        })
    }

}

