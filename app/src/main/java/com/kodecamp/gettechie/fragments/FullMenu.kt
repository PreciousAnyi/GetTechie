package com.kodecamp.gettechie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kodecamp.gettechie.R


class FullMenu : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        val menuBackBtn : ImageView? = activity?.findViewById(R.id.fullMenuBackBtn)
        menuBackBtn?.setOnClickListener{
//            findNavController().navigate(R.id.action_fullMenuFragment_to_bottomMenuFragment)
        }
    }



}