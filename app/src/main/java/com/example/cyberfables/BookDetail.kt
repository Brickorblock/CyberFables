package com.example.cyberfables

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"

class BookDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Fable? = null

    // TODO: change constructor to make fable non null (currently fable can be null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getParcelable(ARG_PARAM1)
        }
    }

    //TODO allow swipe on detail fragment to change books
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var root = inflater.inflate(R.layout.fragment_book_detail, container, false)
        val title: TextView = root.findViewById(R.id.textView) as TextView
        title.text = param1!!.title
        val blurb: TextView = root.findViewById(R.id.textView2) as TextView
        blurb.text = param1!!.blurb
        val cover: ImageView = root.findViewById(R.id.coverImage) as ImageView
        cover.setImageResource(param1!!.coverImg)

        return root;
    }

    companion object {
        @JvmStatic
        fun newInstance(book: Fable) =
            BookDetail().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM1, book)
                }
            }
    }
}
