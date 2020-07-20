package com.example.cyberfables

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookDetailFragment(val fable: Fable) : Fragment() {

    //TODO allow swipe on detail fragment to change books
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_book_detail, container, false)
        val title: TextView = root.findViewById(R.id.textView) as TextView
        title.text = fable.title
        val blurb: TextView = root.findViewById(R.id.textView2) as TextView
        blurb.text = fable.blurb
        val cover: ImageView = root.findViewById(R.id.imageView) as ImageView
        cover.setImageResource(fable.coverImg)

        return root;
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // handle switching fragments on button press
        readButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_bookshelfFragment_to_readerFragment
            )
        )
    }
}
