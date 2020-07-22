package com.example.cyberfables.interactives

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cyberfables.R
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.*
import kotlinx.android.synthetic.main.fragment_littlered_result1.view.*

class LittleredResult1Fragment() : Fragment() {

    private var correctChoice: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // grab args
        correctChoice =
            requireArguments().getBoolean(LittleredInteractive1Fragment.KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_littlered_result1, container, false)
        
        val pageImage = root.pageImage

        if (correctChoice) {
            pageImage.setImageResource(R.drawable.littlered_3_decision1_correct)
        } else {
            pageImage.setImageResource(R.drawable.littlered_3_decision1_incorrect)
        }

        // Inflate the layout for this fragment
        return root
    }

}