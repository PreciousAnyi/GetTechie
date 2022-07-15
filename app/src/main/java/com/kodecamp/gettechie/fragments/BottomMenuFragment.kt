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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bottomNavView: NavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        bottomNavView?.setOnClickListener{
            when(it.id){
                R.id.nav_home -> Toast.makeText(context, "Pressed home", Toast.LENGTH_LONG).show()
                R.id.nav_explore -> Toast.makeText(context, "Pressed explore", Toast.LENGTH_LONG).show()
                R.id.nav_downloads -> Toast.makeText(context, "Pressed downloads", Toast.LENGTH_LONG).show()
                R.id.nav_menu -> Toast.makeText(context, "Pressed menu", Toast.LENGTH_LONG).show()
                else -> {
                }

            }
            true
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bottom_menu, container, false)
    }


}