package com.example.cyberfables

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 * Use the [BookshelfFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookshelfFragment : Fragment() {
    private val TAG = "BookshelfFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "test3")
        //todo change dummy dataset
        val dataset = arrayOf("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8")
        root = inflater.inflate(R.layout.fragment_bookshelf, container, false)

        // Init recyclerview
        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView).apply{
            setHasFixedSize(true)

            // set horizontal layout
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // set adapter
            adapter = BookAdapter(dataset)

        }

        Log.d(TAG, "test")

        // Inflate the layout for this fragment
        return root
    }

}
