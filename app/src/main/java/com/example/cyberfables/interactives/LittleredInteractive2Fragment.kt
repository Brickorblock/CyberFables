package com.example.cyberfables.interactives

import android.R.color
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import kotlinx.android.synthetic.main.fragment_littlered_interactive2.*
import kotlinx.android.synthetic.main.fragment_storypage.view.*


class LittleredInteractive2Fragment : Fragment() {

    companion object {
        val KEY = "LittleredInteractive2Fragment"
    }

    private lateinit var pageImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_littlered_interactive2, container, false)
        pageImage = root.pageImage
        return root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(pageImage.context)
            .load(R.drawable.littlered_6_decision2)
            .dontAnimate()
            .thumbnail(0.1f)
            .into(pageImage)

        Glide.with(hitbox.context)
            .load(R.drawable.littlered_6_decision2_hitboxes)
            .into(hitbox)



        // handles custom hitbox touch zones on a static image.
        // See Helper.getHitboxColour() for more details
        hitbox.setOnTouchListener(OnTouchListener { v, ev ->
            val action = ev.action
            val evX = ev.getX().toInt()
            val evY = ev.getY().toInt()

            Log.d("LittleredInteractive", "evX = $evX, evY = $evY")

            if (action == MotionEvent.ACTION_UP) {
                val pixelColour: Int = Helper().getHitboxColour(hitbox, evX, evY)
                Log.d("LittleredInteractive", "pixelColour = $pixelColour")

                determineChoice(pixelColour)
            }

            true
        })
    }

    fun determineChoice(pixelColour: Int) {
        var choiceMade = false
        lateinit var choiceBundle: Bundle

        val red = pixelColour shr 16 and 0xff
        val blue = pixelColour and 0xff

        if (pixelColour == Color.WHITE){
            Toast.makeText(
                context,
                "Make a decision on the phone!",
                Toast.LENGTH_SHORT
            ).show()
        }else if (red > 100){
            choiceBundle = bundleOf(KEY to true)
            choiceMade = true
        }else if (blue > 100){
            choiceBundle = bundleOf(KEY to false)
            choiceMade = true
        }

        //navigate to results based on choice
        if (choiceMade) {
            Log.d("LittleredInteractive", "bundle = $choiceBundle")

            (context as MainActivity).navController.navigate(
                R.id.action_littleredInteractive2Fragment_to_littleredResult2Fragment,
                choiceBundle
            )
        }
    }

}

