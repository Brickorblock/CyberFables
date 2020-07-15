package com.example.cyberfables.reader

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_storypage.view.*

class ReaderAdapter(
    val fable: Fable

): RecyclerView.Adapter<ReaderAdapter.ReaderViewHolder>() {
    private lateinit var mRecyclerView: RecyclerView
    private var positionCounter = -1

    inner class ReaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReaderViewHolder {
        Log.d("ReaderAdapter", "OnCreateViewHolder")

        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_storypage, parent, false)
        return ReaderViewHolder(v)
    }

    override fun getItemCount() = fable.pageCount

    override fun onBindViewHolder(holder: ReaderViewHolder, position: Int) {

        val curr = fable.pages[position]
        // prev paged used to check interactive
        var prev = curr
        if (position > 0) {
            prev = fable.pages[position - 1]
        }

        Log.d("ReaderAdapter", "itemCount = $itemCount, currPos = $position, currImg = $curr")
        holder.itemView.pageImage.setImageResource(curr)

        //launch interactive fragment when reached - checking using prev page because adapter always
        //loads one position ahead so curr page is actually the next page.
        if (checkInteractive(prev)) {
            (mRecyclerView.context as MainActivity).navController.navigate(R.id.action_readerFragment_to_littleredInteractive1Fragment)
        }
    }

    private fun checkInteractive(page: Int): Boolean {

        var interactiveReached = false

        Log.d("ReaderAdapter", "checkInteractivePosition, position = $page")

        //todo change hardcode
        if (page == R.drawable.littlered_3_decision1) {
            interactiveReached = true
        }

        return interactiveReached
    }

    // used to reference recyclerview within onBindViewHolder
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

}