package com.example.cyberfables.interactives

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.PasswordMinigame
import com.example.cyberfables.entities.PasswordQuestion
import kotlinx.android.synthetic.main.fragment_tortoise_instruction.*
import kotlin.random.Random

class TortoiseInstructionFragment : Fragment() {

    companion object {
        const val KEY = "TortoiseInstruction"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tortoise_instruction, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // handles custom hitbox touch zones on a static image.
        // See Helper.getHitboxColour() for more details
        hitboxes.setOnTouchListener(View.OnTouchListener { v, ev ->
            val action = ev.action
            val evX = ev.getX().toInt()
            val evY = ev.getY().toInt()

            if (action == MotionEvent.ACTION_UP) {
                val pixelColour: Int = Helper().getHitboxColour(hitboxes, evX, evY)

                determineChoice(pixelColour)
            }

            true
        })

    }

    fun determineChoice(pixelColour: Int) {
        lateinit var gameBundle: Bundle

        when (pixelColour) {
            // correct choice
            Color.BLUE -> {
                val gameInstance = initMinigame()
                gameBundle = bundleOf(KEY to gameInstance)

                (context as MainActivity).navController.navigate(
                    R.id.action_tortoiseInstructionFragment_to_tortoiseQuestionFragment,
                    gameBundle
                )
            }
        }
    }

    fun initMinigame(): PasswordMinigame {
        val passwordQuestions = generateQuestions()

        val gameInstance = PasswordMinigame(passwordQuestions)

        return gameInstance
    }

    // randomly fetches 6 questions from the bank
    fun generateQuestions(): MutableList<PasswordQuestion> {
        var questions = mutableListOf<PasswordQuestion>()
        var previouslyAdded = arrayListOf<Int>()

        val questionBank = mutableListOf(
            PasswordQuestion("5occ3r#R0x.", true, true, true, true, true, true),
            PasswordQuestion("hunter123", false, false, true, false, true, false),
            PasswordQuestion("password", false, false, false, false, true, false),
            PasswordQuestion("i<3P@sTA!&p1zZ4", true, true, true, true, true, true),
            PasswordQuestion("SoccerRocks123!", false, true, true, true, true, false),
            PasswordQuestion("Hansel2005", false, false, true, true, true, false),
            PasswordQuestion("|!TT|3-r3D_H00D", true, true, true, true, true, true),
            PasswordQuestion("!!!!!1", false, true, true, false, false, false),
            PasswordQuestion("M-nn=u5hr00m", true, true, true, true, true, true),
            PasswordQuestion("#2Le0a0NHs5^", true, true, true, true, true, true)
        )

        var i = 0
        while (i < 6) {
            var dupeFound = false
            val index = Random.nextInt(0, questionBank.size)
            Log.d("TortoiseInstruction", "generated index $index")

            // check if duplicate
            for (prevIndex in previouslyAdded) {
                Log.d("TortoiseInstruction", "Checking prevIndex $prevIndex...")
                if (index == prevIndex) {
                    Log.d("TortoiseInstruction", "dupe found")
                    dupeFound = true
                }
            }

            if (!dupeFound) {
                previouslyAdded.add(index)
                questions.add(questionBank[index])
                i++
            }

        }

        return questions
    }

}