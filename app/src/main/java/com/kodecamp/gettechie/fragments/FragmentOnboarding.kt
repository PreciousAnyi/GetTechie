package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.adapter.ViewPagerAdapter


class FragmentOnboarding : Fragment(R.layout.fragment_onboarding) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //fragments for the onboarding screens here
        val fragmentList = arrayListOf<Fragment>(
            FirstOnboardingScreen(),
            SecondOnboardingScreen(),
            ThirdOnboardingScreen()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            parentFragmentManager,
            lifecycle
        )
        view.findViewById<ViewPager2>(R.id.viewpager2).adapter = adapter

    }

}