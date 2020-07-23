package com.example.cyberfables.interactives

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.cyberfables.BookDetailFragment
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.database.AppDatabase
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_littlered_interactive1.*
import kotlinx.android.synthetic.main.fragment_littlered_result1.*
import kotlinx.android.synthetic.main.fragment_littlered_result1.view.*

class LittleredResult1Fragment() : Fragment() {

    private var correctChoice: Boolean = false
    private lateinit var nextButton: Button
    private lateinit var fable: Fable

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
        nextButton = root.nextButton

        if (correctChoice) {
            pageImage.setImageResource(R.drawable.littlered_3_decision1_correct)
        } else {
            pageImage.setImageResource(R.drawable.littlered_3_decision1_incorrect)
        }

        // get the littleRed fable
        val db = AppDatabase.getDatabase(requireContext())
        fable = db.fableDao().getFable(1)
        //change the page to open to the one after the minigame
        fable.pageToOpenOn = fable.pages.indexOf(fable.interactivePages?.get(0))
        //remove the interactive minigame we just used
        fable.interactivePages?.removeAt(0)
        fable.pages.removeAt(fable.pageToOpenOn)
        fable.interactiveFragmentsNav?.removeAt(0)

        // Inflate the layout for this fragment
        return root
    }

    override fun onStart() {
        super.onStart()

        // play animations
        val buttonAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in_slow);
        buttonAnim.startTime = AnimationUtils.currentAnimationTimeMillis() + 2000
        nextButton.animation = buttonAnim
        //switch back to the reader
        var fableContinueBundle = bundleOf(BookDetailFragment.KEY to fable)

        // handle switching fragments on button press
        nextButton.setOnClickListener {
            //pass thru Fable & navigate
            (context as MainActivity).navController.navigate(R.id.action_littleredResult1Fragment_to_readerFragment, fableContinueBundle)
        }


    }

}