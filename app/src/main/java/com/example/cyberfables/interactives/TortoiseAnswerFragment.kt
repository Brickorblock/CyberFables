package com.example.cyberfables.interactives

import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cyberfables.Helper
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.PasswordMinigame
import com.example.cyberfables.entities.PasswordQuestion
import kotlinx.android.synthetic.main.fragment_tortoise_answer.*
import kotlinx.android.synthetic.main.fragment_tortoise_gameover.*

class TortoiseAnswerFragment : Fragment() {

    private lateinit var gameInstance: PasswordMinigame
    private lateinit var currQ: PasswordQuestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //grab args
        gameInstance = requireArguments().getParcelable(TortoiseInstructionFragment.KEY)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_tortoise_answer, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currQ = gameInstance.questions[0]

        val userCorrect = gameInstance.userCorrect

        passwordText.text = currQ.password

        var sound: Int

        if (userCorrect) {
            resultText.text = "Correct - Good Job!"
            sound = R.raw.tortoise_right_answer_sound_effect
            resultText.setTextColor(resources.getColor(R.color.colorGreen))
            hareImage.setImageResource(R.drawable.hare_happy)
        } else {
            resultText.text = "Incorrect - Good Try!"
            sound = R.raw.tortoise_incorrect_sound_effect
            resultText.setTextColor(resources.getColor(R.color.colorRed))
            hareImage.setImageResource(R.drawable.hare_sad)
        }

        Helper().playSound(hareImage.context, sound)

        setAnswersText()

        nextButton.setOnClickListener {
            // remove current q so it doesn't get accessed again
            gameInstance.questions.removeAt(0)
            val gameBundle = bundleOf(TortoiseInstructionFragment.KEY to gameInstance)
            // check if we are on the last question or not
            if (gameInstance.questions.size == 0 || gameInstance.lives == 0) {
                // game is finished, go to gameover screen
                (context as MainActivity).navController.
                navigate(R.id.action_tortoiseAnswerFragment_to_tortoiseGameoverFragment, gameBundle)
            } else {
                // game is not finished, go to next q
                (context as MainActivity).navController.
                navigate(R.id.action_tortoiseAnswerFragment_to_tortoiseQuestionFragment, gameBundle)
            }
        }
    }

    fun setAnswersText(){
        if (currQ.hasSymbols) {
            symbolsText.text = "Has Symbols (@\$%*) ✓"
            symbolsText.setTextColor(resources.getColor(R.color.colorGreen))
        } else {
            symbolsText.text = "No Symbols (@\$%*)"
            symbolsText.setTextColor(resources.getColor(R.color.colorRed))
        }

        if (currQ.hasNumbers) {
            numbersText.text = "Has Numbers (1234) ✓"
            numbersText.setTextColor(resources.getColor(R.color.colorGreen))
        } else {
            numbersText.text = "No Numbers (1234)"
            numbersText.setTextColor(resources.getColor(R.color.colorRed))
        }

        if (currQ.hasUpper) {
            upperText.text = "Has Uppercase (ABCD) ✓"
            upperText.setTextColor(resources.getColor(R.color.colorGreen))
        } else {
            upperText.text = "No Uppercase (ABCD)"
            upperText.setTextColor(resources.getColor(R.color.colorRed))
        }

        if (currQ.hasLower) {
            lowerText.text = "Has Lowercase (abcd) ✓"
            lowerText.setTextColor(resources.getColor(R.color.colorGreen))
        } else {
            lowerText.text = "No Lowercase (abcd)"
            lowerText.setTextColor(resources.getColor(R.color.colorRed))
        }

        if (currQ.hardToGuess) {
            guessText.text = "Hard to Guess ✓"
            guessText.setTextColor(resources.getColor(R.color.colorGreen))
        } else {
            guessText.text = "Easy to Guess"
            guessText.setTextColor(resources.getColor(R.color.colorRed))
        }
    }
}