package com.example.cyberfables.reader

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cyberfables.R

class ReaderFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reader, container, false)

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = root.findViewById(R.id.readerPager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ReaderPagerAdapter(this)
        viewPager.adapter = pagerAdapter

        // Inflate the layout for this fragment
        return root
    }

    private inner class ReaderPagerAdapter(f: Fragment): FragmentStateAdapter(f) {
        override fun getItemCount(): Int = 10

        override fun createFragment(position: Int): Fragment = StoryPageFragment()

    }


}