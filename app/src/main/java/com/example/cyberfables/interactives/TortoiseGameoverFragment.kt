package com.example.cyberfables.interactives

import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.SoundMaker
import com.example.cyberfables.entities.PasswordMinigame
import kotlinx.android.synthetic.main.fragment_tortoise_gameover.*

class TortoiseGameoverFragment : Fragment() {

    private lateinit var gameInstance: PasswordMinigame

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //grab args
        gameInstance = requireArguments().getParcelable(TortoiseInstructionFragment.KEY)!!

        var sound = R.raw.tortoise_game_over
        if(gameInstance.userCorrect) sound = R.raw.tortoise_win
        try {
            SoundMaker.playSound(sound)
        } catch (e: KotlinNullPointerException) {
            Thread.dumpStack()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tortoise_gameover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (gameInstance.lives > 0) {
            gameoverText.text = "Well Done!"
            tortoiseImage.setImageResource(R.drawable.tortoise_happy)
            scoreText.text = "You Got ${gameInstance.score} / ${gameInstance.totalSize} Correct!"
        } else {
            gameoverText.text = "Game Over!"
            tortoiseImage.setImageResource(R.drawable.tortoise_sad)
            scoreText.text = "You Ran Out of Lives! (You Got ${gameInstance.score} Correct)"
        }


        backButton.setOnClickListener {
            (context as MainActivity).navController.popBackStack()
        }
        replayButton.setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_tortoiseGameoverFragment_to_tortoiseInstructionFragment)
        }
    }
}