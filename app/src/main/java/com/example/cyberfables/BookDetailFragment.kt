package com.example.cyberfables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*
import kotlinx.android.synthetic.main.fragment_littlered_interactive.*

class BookDetailFragment() : Fragment() {
    var fable: Fable? = null

    constructor(fable: Fable) : this() {
        this.fable = fable
    }

    companion object{
        val KEY = "BookDetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (fable != null) {
            titleText.text = fable!!.title
            blurbText.text = fable!!.blurb
            teachesText.text = fable!!.teaches

            coverImage.setImageResource(fable!!.coverImg)

/*sometime when i load it with glide i see the images loading in
might feel more fluid to just set the image resource
            Glide.with(coverImage.context)
                .load(fable!!.coverImg)
                .dontAnimate()
                .into(coverImage)
*/
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (fable != null) {
            // bundle for passing Fable
            // note: this is a pretty neat way of doing bundles in Kotlin
            // from https://stackoverflow.com/questions/50934760/is-it-possible-to-send-arguments-other-than-string-or-integer-in-androids-new-n
            var fableBundle = bundleOf(KEY to fable)

            // handle switching fragments on button press
            readButton.setOnClickListener {
                //pass thru Fable & navigate
                (context as MainActivity).navController.navigate(R.id.action_bookshelfFragment_to_readerFragment, fableBundle)
            }
        }

    }
}
