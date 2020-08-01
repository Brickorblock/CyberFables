package com.example.cyberfables.interactives

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.SoundMaker
import com.example.cyberfables.entities.PasswordMinigame
import com.example.cyberfables.entities.PasswordQuestion
import kotlinx.android.synthetic.main.fragment_littlered_result.*
import kotlinx.android.synthetic.main.fragment_littlered_result.nextButton
import kotlinx.android.synthetic.main.fragment_tortoise_interactive.*

class TortoiseInteractiveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_tortoise_interactive, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(image.context)
            .load(R.drawable.tortoise_13)
            .dontAnimate()
            .thumbnail(0.1f)
            .into(image)

        SoundMaker.playSound(image.context, R.raw.tortoise_end_song)

        nextButton.setOnClickListener {

            (context as MainActivity).navController.
            navigate(R.id.action_tortoiseInteractiveFragment_to_tortoiseInstructionFragment)
        }
    }


}