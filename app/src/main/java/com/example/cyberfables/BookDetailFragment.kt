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
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

class BookDetailFragment(val fable: Fable) : Fragment() {

    companion object{
        val KEY = "BookDetailFragment"
    }


    //TODO allow swipe on detail fragment to change books
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_book_detail, container, false)
        val title: TextView = root.textView
        title.text = fable.title
        val blurb: TextView = root.textView2
        blurb.text = fable.blurb
        val cover: ImageView = root.imageView
        cover.setImageResource(fable.coverImg)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

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
