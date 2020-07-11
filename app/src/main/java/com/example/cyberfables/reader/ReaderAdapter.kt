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
        Log.d("ReaderAdapter", "itemCount = $itemCount, currPos = $position, currImg = $curr")
        holder.itemView.pageImage.setImageResource(curr)

        //holder.itemView.textView.setText("Position = " + position)

        //launch interactive fragment when reached
        if (checkInteractivePosition(position)) {
            //todo currently a bug - for some reason sometimes the position skips ahead by 1
            (mRecyclerView.context as MainActivity).navController.navigate(R.id.action_readerFragment_to_littleredInteractive1Fragment)

            //holder.itemView.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_readerFragment_to_littleredInteractive1Fragment))
        }
    }

    private fun checkInteractivePosition(position: Int): Boolean {

        var interactiveReached = false

        Log.d("ReaderAdapter", "checkInteractivePosition, position = $position")

        //todo change hardcode
        if (position == 5) {
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