package com.example.cyberfables.reader

import android.media.AudioAttributes
import android.media.SoundPool
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.cyberfables.BookDetailFragment
import com.example.cyberfables.R
import com.example.cyberfables.entities.Fable
import kotlinx.android.synthetic.main.fragment_reader.view.*


class ReaderFragment : Fragment() {

    private val TAG = "ReaderFragment"

    private lateinit var soundPool: SoundPool

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reader, container, false)

        //grab fable from bundle args (from BookDetailFragment)
        val fable = (requireArguments().get(BookDetailFragment.KEY) as Fable)

        //get soundMap
        val soundMap = getSoundPool(fable)

        // Instantiate a ViewPager2 and a PagerAdapter.
        val viewPager = root.readerPager
        viewPager.setPageTransformer(DepthPageTransformer())
        // The pager adapter, which provides the pages to the view pager widget.
        viewPager.adapter = ReaderAdapter(fable, soundMap, soundPool)
        //set the page the viewpager should show
        viewPager.setCurrentItem(fable.pageToOpenOn, false)


        //play music for first page
        val firstPage = fable.pages[fable.pageToOpenOn]
        soundPool.setOnLoadCompleteListener { soundPool, sampleId, status ->
            if (fable.sounds.containsKey(firstPage)){
                soundPool!!.play(soundMap.get(firstPage)!!, 1F, 1F, 1, 0, 1F)
            };
        }
        // Inflate the layout for this fragment
        return root
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun getSoundPool(fable: Fable): HashMap<Int, Int> {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_GAME)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()
        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()
        val soundMap = HashMap<Int,Int>()
        for ((key, value) in fable.sounds) {
            soundMap.put(key, soundPool.load(context,value,1))
        }
        return soundMap
    }

    override fun onDestroyView() {
        soundPool.release()
        super.onDestroyView()
    }

    // handles custom pagination animation
    // source: https://developer.android.com/training/animation/screen-slide-2#depth-page
    @RequiresApi(21)
    class DepthPageTransformer : ViewPager2.PageTransformer {

        private val minScale = 0.75f

        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                when {
                    (position < -1) -> { // [-Infinity,-1)
                        // This page is way off-screen to the left.
                        alpha = 0f
                    }
                    (position <= 0) -> { // [-1,0]
                        // Use the default slide transition when moving to the left page
                        alpha = 1f
                        translationX = 0f
                        translationZ = 0f
                        scaleX = 1f
                        scaleY = 1f
                    }
                    (position <= 1) -> { // [0,1]
                        // Fade the page out.
                        alpha = 1 - position

                        // Counteract the default slide transition
                        translationX = pageWidth * -position
                        // Move it behind the left page
                        translationZ = -1f

                        // Scale the page down (between MIN_SCALE and 1)
                        val scaleFactor = (minScale + (1 - minScale) * (1 - Math.abs(position)))
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    else -> { // (1,+Infinity]
                        // This page is way off-screen to the right.
                        alpha = 0f
                    }
                }
            }
        }
    }
}