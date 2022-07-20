package com.kodecamp.gettechie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kodecamp.gettechie.R


class FullMenu : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_full_menu, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val settingsBtn : CardView? = activity?.findViewById(R.id.cV5)
        settingsBtn?.setOnClickListener{
            findNavController().navigate(R.id.action_fullMenu_to_settingsFragment)
        }
        val ProfileBtn : CardView? = activity?.findViewById(R.id.cV2)
        ProfileBtn?.setOnClickListener{
            findNavController().navigate(R.id.action_fullMenu_to_profileFragment)
        }
    }



}