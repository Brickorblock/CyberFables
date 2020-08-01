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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.view.*
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.*
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.hitboxes
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.view.*
import kotlinx.android.synthetic.main.fragment_littlered_interactive2.*
import kotlinx.android.synthetic.main.fragment_littlered_interactive2.view.*
import kotlinx.android.synthetic.main.fragment_littlered_result1.view.*
import kotlinx.android.synthetic.main.fragment_storypage.*
import kotlinx.android.synthetic.main.fragment_storypage.view.*
import kotlinx.android.synthetic.main.fragment_storypage.view.pageImage


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


        Glide.with(hitboxes.context)
            .load(R.drawable.littlered_6_decision2_hitboxes)
            .dontAnimate()
            .thumbnail(0.1f)
            .into(hitboxes)
        Glide.with(pageImage.context)
            .load(R.drawable.littlered_6_decision2)
            .dontAnimate()
            .thumbnail(0.1f)
            .into(pageImage)


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
                R.id.action_littleredInteractive2Fragment_to_littleredResult2Fragment,
                choiceBundle
            )
        }
    }

}

