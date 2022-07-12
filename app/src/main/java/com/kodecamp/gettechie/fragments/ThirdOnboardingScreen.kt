package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kodecamp.gettechie.R


class ThirdOnboardingScreen : Fragment() {
    private lateinit var button: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_onboarding_screen, container, false)
        view.findViewById<Button>(R.id.signup_button).setOnClickListener {

            findNavController().navigate(R.id.action_fragmentOnboarding_to_signUpFragment)
        }
        view.findViewById<TextView>(R.id.loginTV).setOnClickListener {
            findNavController().navigate(R.id.action_fragmentOnboarding_to_loginFragment)
        }

        return view
    }


}