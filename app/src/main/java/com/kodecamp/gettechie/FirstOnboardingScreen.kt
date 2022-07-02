package com.kodecamp.gettechie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2


class FirstOnboardingScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first_onboarding_screen, container, false)

        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpager2)
        view.findViewById<CardView>(R.id.skip).setOnClickListener {

            viewpager?.currentItem = 2
        }
        return view
    }


}