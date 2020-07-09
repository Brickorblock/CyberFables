package com.example.cyberfables.reader

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cyberfables.R
import kotlinx.android.synthetic.main.fragment_reader.view.*

class ReaderFragment : Fragment() {
    private val TAG = "ReaderFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reader, container, false)

        // Instantiate a ViewPager2 and a PagerAdapter.
        val viewPager = root.readerPager

        // The pager adapter, which provides the pages to the view pager widget.

        val readerAdapter = ReaderAdapter(arrayListOf(R.drawable.test_cover1, R.drawable.test_cover2))
        viewPager.adapter = readerAdapter

        // Inflate the layout for this fragment
        return root
    }


}