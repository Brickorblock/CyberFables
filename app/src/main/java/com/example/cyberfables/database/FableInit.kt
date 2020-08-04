package com.example.cyberfables.database

import com.example.cyberfables.R
import com.example.cyberfables.entities.Fable

class FableInit {
    fun initFables(): ArrayList<Fable> {

        val fable1 = Fable(
            0,
            "Safe & Secure Wins the Race!",
            "Join the Tortoise and his best friend the Hare as they discover the challenges of owning a social media account by learning the importance of a strong password.",
            "Passwords, Online Security",
            R.drawable.fable1_cover,
            R.drawable.fable1_icon,
            arrayListOf(
                R.drawable.tortoise_1,
                R.drawable.tortoise_2,
                R.drawable.tortoise_3,
                R.drawable.tortoise_4,
                R.drawable.tortoise_5,
                R.drawable.tortoise_6,
                R.drawable.tortoise_7,
                R.drawable.tortoise_8,
                R.drawable.tortoise_9,
                R.drawable.tortoise_10,
                R.drawable.tortoise_11,
                R.drawable.tortoise_12,
                R.drawable.tortoise_13,
                R.drawable.passwords_1,
                R.drawable.passwords_3,
                R.drawable.passwords_4,
                R.drawable.passwords_5,
                R.drawable.passwords_5
            ),
            mutableListOf(R.drawable.passwords_5),
            mutableListOf(R.id.action_readerFragment_to_tortoiseInteractiveFragment),
            hashMapOf<Int, Int>(
                R.drawable.tortoise_1 to  R.raw.tortoise_snore,
                R.drawable.tortoise_2 to R.raw.tortoise_idea,
                R.drawable.tortoise_3 to R.raw.tortoise_ding_and_noise,
                R.drawable.tortoise_4 to R.raw.tortoise_disappointed_noise1,
                R.drawable.tortoise_6 to R.raw.tortoise_realisation_ah,
                R.drawable.tortoise_7 to R.raw.tortoise_ring_phone,
                R.drawable.tortoise_8 to R.raw.tortoise_phone_chatter,
                R.drawable.tortoise_9 to R.raw.tortoise_dingdong,
                R.drawable.tortoise_10 to R.raw.tortoise_realisation_ah2,
                R.drawable.tortoise_12 to R.raw.tortoise_yay,
                R.drawable.tortoise_13 to R.raw.tortoise_end_song
            ),
            bgMusic =  Pair(R.raw.fable_1_music,0.1F),
            lastStoryPage = R.drawable.tortoise_13
        )

        val fable2 = Fable(
            1,
            "Little Red & the Cyber Wolf",
            "Follow Little Red Riding Hood’s journey into owning her first Facebook account, where she is tricked into trusting someone who isn't who they say they are...",
            "Phishing, Digital Stranger Danger",
            R.drawable.fable2_cover,
            R.drawable.fable2_icon,
            // this is the first "chapter" - up to the first interactive fragment
            // note - must include one page after the decision due to how the adapter handles
            // interactive checks (looks at prev and curr pages)
            arrayListOf(
                R.drawable.littlered_1,
                R.drawable.littlered_2,
                R.drawable.littlered_3,
                R.drawable.littlered_3_decision1,
                R.drawable.littlered_4,
                R.drawable.littlered_5,
                R.drawable.littlered_6,
                R.drawable.littlered_6_decision2,
                R.drawable.littlered_7,
                R.drawable.littlered_8_badend,
                R.drawable.littlered_8_goodend,
                R.drawable.littlered_9,
                R.drawable.littlered_10_badend,
                R.drawable.littlered_10_goodend,
                R.drawable.littlered_11_converged,
                R.drawable.littlered_12_converged,
                R.drawable.phishing_1,
                R.drawable.phishing_2,
                R.drawable.phishing_3,
                R.drawable.phishing_4
            ),
            mutableListOf(R.drawable.littlered_3_decision1, R.drawable.littlered_6_decision2),
            mutableListOf(
                R.id.action_readerFragment_to_littleredInteractive1Fragment,
                R.id.action_readerFragment_to_littleredInteractive2Fragment),
            hashMapOf<Int, Int>(
                R.drawable.littlered_1 to  R.raw.lilred_morning_birds,
                R.drawable.littlered_2 to R.raw.lilred_right_answer_sound_effect,
                R.drawable.littlered_3 to R.raw.lilred_hmm1,
                R.drawable.littlered_4 to R.raw.lilred_notification,
                R.drawable.littlered_5 to R.raw.lilred_notification,
                R.drawable.littlered_6 to R.raw.lilred_gasp,
                R.drawable.littlered_7 to R.raw.lilred_owl_hoot,
                R.drawable.littlered_8_goodend to R.raw.lilred_gasp,
                R.drawable.littlered_8_badend to R.raw.lilred_gasp,
                R.drawable.littlered_9 to R.raw.lilred_error,
                R.drawable.littlered_10_badend to  R.raw.hansel_whimper,
                R.drawable.littlered_12_converged to R.raw.lilred_hmm1
            ),
            bgMusic = Pair(R.raw.fable_2_music,0.1F),
            lastStoryPage = R.drawable.littlered_12_converged
        )

        val fable3 = Fable(
            2,
            "Hansel & Gretel's Digital Footprint",
            "Explore a magical forest called \"The Internet\" with Hansel & Gretel, where they learn how everything they do online leaves a footprint that anyone can follow.",
            "Digital Footprints, Digital Privacy",
            R.drawable.fable3_cover,
            R.drawable.fable3_icon,
            arrayListOf(
                R.drawable.hansel_1,
                R.drawable.hansel_2,
                R.drawable.hansel_3,
                R.drawable.hansel_4,
                R.drawable.hansel_5,
                R.drawable.hansel_6,
                R.drawable.hansel_7,
                R.drawable.hansel_8,
                R.drawable.hansel_9,
                R.drawable.hansel_10,
                R.drawable.hansel_11,
                R.drawable.hansel_12,
                R.drawable.hansel_13,
                R.drawable.hansel_14,
                R.drawable.hansel_15,
                R.drawable.hansel_16,
                R.drawable.hansel_17,
                R.drawable.hansel_18,
                R.drawable.hansel_19,
                R.drawable.hansel_20,
                R.drawable.hansel_21,
                R.drawable.hansel_22,
                R.drawable.hansel_23,
                R.drawable.footprints_1,
                R.drawable.footprints_2,
                R.drawable.footprints_3,
                R.drawable.footprints_4
            ),
            //empty lists = no interactive content
            mutableListOf(), mutableListOf(),
            hashMapOf<Int, Int>(
                R.drawable.hansel_1 to  R.raw.hansel_humming,
                R.drawable.hansel_2 to R.raw.hansel_magic_ringing,
                R.drawable.hansel_5 to R.raw.hansel_footsteps,
                R.drawable.hansel_6 to R.raw.hansel_hmm1,
                R.drawable.hansel_7 to R.raw.hansel_mock_laugh,
                R.drawable.hansel_9 to R.raw.hansel_running_grass,
                R.drawable.hansel_10 to R.raw.hansel_notification,
                R.drawable.hansel_11 to R.raw.hansel_footsteps,
                R.drawable.hansel_12 to R.raw.hansel_notification_sad,
                R.drawable.hansel_13 to R.raw.hansel_hmm1,
                R.drawable.hansel_14 to R.raw.hansel_footsteps,
                R.drawable.hansel_15 to  R.raw.hansel_notification,
                R.drawable.hansel_19 to R.raw.hansel_footsteps,
                R.drawable.hansel_20 to R.raw.hansel_gasp,
                R.drawable.hansel_23 to R.raw.hansel_end_jingle
            ),
            bgMusic = Pair (R.raw.fable_3_music, 0.1F),
            lastStoryPage = R.drawable.hansel_23
        )
        var fables : ArrayList<Fable> = ArrayList()
        fables.add(fable1)
        fables.add(fable2)
        fables.add(fable3)

        return fables
    }

}