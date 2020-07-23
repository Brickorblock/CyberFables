package com.example.cyberfables.reader

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cyberfables.BookDetailFragment
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_reader.view.*

class ReaderFragment : Fragment(){

    private val TAG = "ReaderFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reader, container, false)

        //grab fable from bundle args (from BookDetailFragment)
        val fable = (requireArguments().get(BookDetailFragment.KEY) as Fable)

        // Instantiate a ViewPager2 and a PagerAdapter.
        val viewPager = root.readerPager
        // The pager adapter, which provides the pages to the view pager widget.
        val readerAdapter = ReaderAdapter(fable)
        viewPager.adapter = readerAdapter
        //set the page the viewpager should show
        viewPager.setCurrentItem(fable.pageToOpenOn, false)
        


        // Inflate the layout for this fragment
        return root
    }


}