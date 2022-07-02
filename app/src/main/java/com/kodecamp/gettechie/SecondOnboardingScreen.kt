package com.kodecamp.gettechie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView


class SecondOnboardingScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second_onboarding_screen, container, false)

        val viewpager = activity?.findViewById<ViewPager2>(R.id.viewpager2)
        view.findViewById<MaterialCardView>(R.id.skip2).setOnClickListener {

            viewpager?.currentItem = 2
        }
        return view
    }


}