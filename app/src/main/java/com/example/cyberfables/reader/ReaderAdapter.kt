package com.example.cyberfables.reader

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberfables.R
import kotlinx.android.synthetic.main.fragment_storypage.view.*

class ReaderAdapter(
    val pages: ArrayList<Int>
): RecyclerView.Adapter<ReaderAdapter.ReaderViewHolder>() {
    inner class ReaderViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReaderViewHolder {
        Log.d("ReaderAdapter", "OnCreateViewHolder")
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_storypage, parent, false)
        return ReaderViewHolder(v)
    }

    override fun getItemCount() = pages.size

    override fun onBindViewHolder(holder: ReaderViewHolder, position: Int) {
        val curr = pages[position]
        Log.d("ReaderAdapter", "itemCount = $itemCount, currPos = $position, currImg = $curr")
        holder.itemView.pageImage.setImageResource(curr)
    }
}