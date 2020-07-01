package com.example.cyberfables

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberfables.entities.Fable

class BookAdapter(private val dataset: ArrayList<Fable>) :
    RecyclerView.Adapter<BookAdapter.MyViewHolder>() {

    class MyViewHolder(val item: View) : RecyclerView.ViewHolder(item){
        val iconImage = item.findViewById<ImageView>(R.id.iconImage)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_view, parent, false) as ConstraintLayout

        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.iconImage.setImageResource(dataset.get(position).iconImg)

    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}