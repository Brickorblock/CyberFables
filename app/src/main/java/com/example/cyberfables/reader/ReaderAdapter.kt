package com.example.cyberfables.reader

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.view.*
import kotlinx.android.synthetic.main.fragment_storypage.view.*

class ReaderAdapter(
    val fable: Fable

) : RecyclerView.Adapter<ReaderAdapter.ReaderViewHolder>() {
    private lateinit var mRecyclerView: RecyclerView

    inner class ReaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReaderViewHolder {
        Log.d("ReaderAdapter", "OnCreateViewHolder")

        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_storypage, parent, false)
        return ReaderViewHolder(v)
    }

    override fun getItemCount() = fable.pages.size

    override fun onBindViewHolder(holder: ReaderViewHolder, position: Int) {

        val curr = fable.pages[position]
        // prev paged used to check interactive
        var prev = curr
        if (position > 0) {
            prev = fable.pages[position - 1]
        }

        Log.d("ReaderAdapter", "itemCount = $itemCount, currPos = $position, currImg = $curr")
        Glide.with(holder.itemView.context)
            .load(curr)
            .dontAnimate()
            .thumbnail(0.5f)
            .into(holder.itemView.pageImage)

        //launch interactive fragment when reached - skip this if there are no interactives in fable
        if (!fable.interactivePages.isNullOrEmpty()) {
            // checking using prev page because adapter always
            // loads one position ahead so curr page is actually the next page.
            if (checkInteractive(prev)) {

                //navigate to the "interactive version" of the page
                (mRecyclerView.context as MainActivity).navController.
                navigate(fable.interactiveFragmentsNav!![0])
            }
        } else {
            Log.d("ReaderAdapter", "No interactive pages - Skipping checkInteractive()...")
        }

    }

    private fun checkInteractive(page: Int): Boolean {

        var interactiveReached = false

        Log.d("ReaderAdapter", "checkInteractivePosition, page = $page")

        for (interactivePage in fable.interactivePages!!) {
            Log.d("ReaderAdapter", "checking interactive = $interactivePage")
            if (page == interactivePage) {
                interactiveReached = true
            }
        }

        Log.d("ReaderAdapter", "checkInteractivePosition, interactiveReached = $interactiveReached")

        return interactiveReached
    }

    // used to reference recyclerview within onBindViewHolder
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

}