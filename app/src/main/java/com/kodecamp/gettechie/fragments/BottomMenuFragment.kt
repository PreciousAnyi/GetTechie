package com.kodecamp.gettechie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.kodecamp.gettechie.R

class BottomMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bottomNavView: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        bottomNavView?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(context, "Pressed home", Toast.LENGTH_LONG).show()
                R.id.nav_explore -> Toast.makeText(context, "Pressed explore", Toast.LENGTH_LONG).show()
                R.id.nav_downloads -> Toast.makeText(context, "Pressed downloads", Toast.LENGTH_LONG).show()
                R.id.nav_menu -> {
                    findNavController().navigate(R.id.action_bottomMenuFragment_to_fullMenuFragment)
                }
                else -> {
                }

            }
            true
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false)
    }


}