package com.example.cyberfables

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BookshelfFragment : Fragment() {
    private val TAG = "BookshelfFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "$TAG - onCreateView")

        // generate dataset by initialising fables
        val dataset = (activity as MainActivity).initFables()
        root = inflater.inflate(R.layout.fragment_bookshelf, container, false)

        // Init recyclerview
        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView).apply{
            setHasFixedSize(true)

            // set horizontal layout
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // set adapter
            adapter = BookAdapter(dataset)

        }

        // Inflate the layout for this fragment
        return root
    }

}
