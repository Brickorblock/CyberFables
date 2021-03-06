package com.example.cyberfables

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cyberfables.database.AppDatabase
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_book_detail.*

class BookshelfFragment: Fragment() {
    private val TAG = "BookshelfFragment"

    private lateinit var recyclerView: RecyclerView
    private lateinit var root: View

    var selectedPosition = 0
    private var prevPosition = 0

    override fun onResume() {
        SoundMaker.StopBgMusic()
        super.onResume()
    }

    // swithces to fable; uses position and prevPosition to determine switching animations
    fun onChosen(fable: Fable) {

        val transaction = childFragmentManager.beginTransaction()
        val position = fable.id
        // specify custom animation
        if (position > prevPosition) {
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        } else if (position < prevPosition) {
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        transaction.replace(R.id.childFragment, BookDetailFragment(fable), "bookDetail")
        transaction.commit()

        prevPosition = position
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "$TAG - onCreateView")

        // generate dataset by initialising fables
        val db = AppDatabase.getDatabase(requireContext())
        val dataset = db.fableDao().getAllFables()
        root = inflater.inflate(R.layout.fragment_bookshelf, container, false)

        // set initial fable selected
        onChosen(dataset[selectedPosition])

        // Init recyclerview
        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)

            // set horizontal layout
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

            // set adapter
            adapter = BookAdapter(dataset as ArrayList<Fable>)

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
                holder.itemView.setBackgroundColor(ContextCompat.getColor(context!!,R.color.colorAccentLight))
            } else {
                //clear highlight
                holder.itemView.setBackgroundColor(0)
            }

            val book = dataset[position]
            holder.iconImage.setImageResource(dataset[position].iconImg)
/*sometime when i load it with glide i see the images loading in
might feel more fluid to just set the image resource
            Glide.with(holder.iconImage.context)
                .load(book.iconImg)
                .dontAnimate()
                .into(holder.iconImage)
*/
            holder.itemView.setOnClickListener {
                // update positions & dataset (for itemview highlighting), then switch fragment
                selectedPosition = position
                onChosen(book)
                notifyDataSetChanged()
            }
        }

        override fun getItemCount(): Int {
            return dataset.size
        }

    }



}
