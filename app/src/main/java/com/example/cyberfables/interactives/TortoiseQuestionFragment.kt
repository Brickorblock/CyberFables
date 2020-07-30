package com.example.cyberfables.interactives

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.PasswordMinigame
import com.example.cyberfables.entities.PasswordQuestion
import kotlinx.android.synthetic.main.fragment_tortoise_question.*
import kotlinx.android.synthetic.main.fragment_tortoise_question.view.*


class TortoiseQuestionFragment : Fragment() {

    private lateinit var gameInstance: PasswordMinigame
    private lateinit var currQ: PasswordQuestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //grab args
        gameInstance = requireArguments().getParcelable(TortoiseInstructionFragment.KEY)!!

        Log.d("TortoiseQuestion", gameInstance.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_tortoise_question, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currQ = gameInstance.questions[0]
        scoreText.setText("Score: ${gameInstance.score}")
        passwordText.text = currQ.password

        //set heart counter
        when (gameInstance.lives) {
            3 -> {
                heart1.setImageResource(R.drawable.heart_filled)
                heart2.setImageResource(R.drawable.heart_filled)
                heart3.setImageResource(R.drawable.heart_filled)
            }

            2 -> {
                heart1.setImageResource(R.drawable.heart_empty)
                heart2.setImageResource(R.drawable.heart_filled)
                heart3.setImageResource(R.drawable.heart_filled)
            }

            1 -> {
                heart1.setImageResource(R.drawable.heart_empty)
                heart2.setImageResource(R.drawable.heart_empty)
                heart3.setImageResource(R.drawable.heart_filled)
            }
        }

        goodButton.setOnClickListener {
            val userAnswer = true
            determineResult(userAnswer)
        }

        badButton.setOnClickListener {
            val userAnswer = false
            determineResult(userAnswer)
        }
    }

    fun determineResult(userAnswer: Boolean): Boolean {

        if (userAnswer == currQ.answer) {
            gameInstance.userCorrect = true
            gameInstance.score++
        } else {
            gameInstance.userCorrect = false
            gameInstance.lives--
        }

        val gameBundle = bundleOf(TortoiseInstructionFragment.KEY to gameInstance)
        (context as MainActivity).navController.
        navigate(R.id.action_tortoiseQuestionFragment_to_tortoiseAnswerFragment, gameBundle)

        return gameInstance.userCorrect
    }
}