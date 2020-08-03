package com.example.cyberfables.reader

import android.media.SoundPool
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cyberfables.MainActivity
import com.example.cyberfables.R
import com.example.cyberfables.SoundMaker
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_storypage.view.*

class ReaderAdapter(
    val fable: Fable,
    private val soundMap: HashMap<Int, Int>,
    private val soundPool: SoundPool?

) : RecyclerView.Adapter<ReaderAdapter.ReaderViewHolder>() {
    private lateinit var mRecyclerView: RecyclerView
    private var mute: Boolean = false

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
        var prevPrev = curr
        if (position > 0) {
            prev = fable.pages[position - 1]
        } else if (position > 1) {
            prevPrev = fable.pages[position - 2]
        }

        Log.d("ReaderAdapter", "itemCount = $itemCount, currPos = $position, currImg = $curr")
        //only load an image if the page is NOT interactive
        if (fable.interactivePages.isNullOrEmpty() or !(fable.interactivePages!!.contains(curr))) {
            Glide.with(holder.itemView.context)
                .load(curr)
                .dontAnimate()
                .thumbnail(0.1f)
                .into(holder.itemView.pageImage)
        }

        //play sounds for pages
        if(soundMap.containsKey(prev) and (prev != fable.pages[fable.pageToOpenOn])){
            soundPool?.play(soundMap.get(prev)!!, 1F, 1F, 1, 0, 1F);
            //remove sound after being played
            soundMap.remove(prev)
        }


        val lastPage = fable.lastStoryPage
        Log.d("ReaderAdapter", "curr = $curr, prev = $prev, lastStoryPage = $lastPage")

        // animate swipe icon hints for first and last pages
        val anim = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.fade_in_left)
        anim.startTime = AnimationUtils.currentAnimationTimeMillis() + 2000
        if (position == 0) {
            // play animations for first page
            holder.itemView.learnText.alpha = 1F
            holder.itemView.swipe_icon.alpha = 1F
            holder.itemView.swipe_icon.animation = anim
            holder.itemView.learnText.alpha = 0F
        } else if (prevPrev == lastPage) {
            // play animations for last page
            Log.d("ReaderAdapter", "in Last Page")
            holder.itemView.learnText.alpha = 1F
            holder.itemView.swipe_icon.alpha = 1F
            holder.itemView.swipe_icon.animation = anim
            holder.itemView.learnText.animation = anim
            //mute the sounds when u get to the last page
            //prevents sounds playing out of order as you swipe backwards
            mute = true
        } else {
            // hide elements when not needed
            holder.itemView.swipe_icon.alpha = 0F
            holder.itemView.learnText.alpha = 0F
        }

        //launch interactive fragment when reached - skip this if there are no interactives in fable
        if (!fable.interactivePages.isNullOrEmpty() &&
            !fable.interactiveFragmentsNav.isNullOrEmpty()) {
            // checking using prev page because adapter always
            // loads one position ahead so curr page is actually the next page.
            if (checkInteractive(prev)) {

                //navigate to the "interactive version" of the page
                (mRecyclerView.context as MainActivity).navController.navigate(fable.interactiveFragmentsNav!![0])
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