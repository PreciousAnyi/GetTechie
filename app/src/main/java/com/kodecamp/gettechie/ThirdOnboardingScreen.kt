package com.kodecamp.gettechie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.kodecamp.gettechie.activities.LoginActivity


class ThirdOnboardingScreen : Fragment() {
    private lateinit var button: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_third_onboarding_screen, container, false)
        view.findViewById<Button>(R.id.signup_button).setOnClickListener {

            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }
        view.findViewById<TextView>(R.id.loginTV).setOnClickListener {
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }


}