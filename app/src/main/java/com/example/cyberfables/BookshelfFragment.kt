package com.example.cyberfables

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cyberfables.entities.Fable

class BookshelfFragment : Fragment() {
    private val TAG = "BookshelfFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var root: View

    //TODO does mainactivity need to know when a book is selected
    private lateinit var listener: OnBookSelected

    private var selectedPosition = 0
    private var prevPosition = 0

    companion object {

        fun newInstance(): BookshelfFragment {
            return BookshelfFragment()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //attach BookshelfFragment
        if (context is OnBookSelected) {
            listener = context
        } else {
            throw ClassCastException("$context must implement OnBookSelected.")
        }
    }

    // swithces to fable; uses position and prevPosition to determine switching animations
    fun onChosen(fable: Fable, position: Int) {

        val transaction = childFragmentManager.beginTransaction()
        // specify custom animation
        if (position > prevPosition) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        } else if (position < prevPosition) {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        transaction.replace(R.id.childFragment, BookDetail.newInstance(fable), "bookDetail")
        transaction.commit()

        prevPosition = position
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "$TAG - onCreateView")

        // generate dataset by initialising fables
        val dataset = (activity as MainActivity).initFables()
        root = inflater.inflate(R.layout.fragment_bookshelf, container, false)

        // set initial fable selected
        onChosen(dataset[selectedPosition], 0)

        // Init recyclerview
        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)

            // set horizontal layout
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // set adapter
            adapter = BookAdapter(dataset)

        }

        // Inflate the layout for this fragment
        return root
    }


    internal inner class BookAdapter(private val dataset: ArrayList<Fable>) :
        RecyclerView.Adapter<BookAdapter.MyViewHolder>() {


        inner class MyViewHolder(val item: View) : RecyclerView.ViewHolder(item) {
            val iconImage = item.findViewById<ImageView>(R.id.iconImage)

        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_view, parent, false) as ConstraintLayout

            return MyViewHolder(v)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // highlight selected itemView
            if (position == selectedPosition){
                holder.itemView.setBackgroundColor(resources.getColor(R.color.colorAccentLight))
            } else {
                //clear highlight
                holder.itemView.setBackgroundColor(0)
            }

            val book = dataset[position]
            holder.iconImage.setImageResource(dataset[position].iconImg)
            holder.itemView.setOnClickListener { listener.onBookSelected(book) }
            holder.itemView.setOnClickListener {
                // update positions & dataset (for itemview highlighting), then switch fragment
                selectedPosition = position
                onChosen(book, position)
                notifyDataSetChanged()
            }
        }

        override fun getItemCount(): Int {
            return dataset.size
        }


    }

    interface OnBookSelected {
        fun onBookSelected(fable: Fable)
    }


}
