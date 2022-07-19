package com.kodecamp.gettechie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.kodecamo.gettechie.adapter.topratedAdapter
import com.kodecamp.gettechie.DataSource
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentTopRatedBinding


class topRatedFragment : Fragment() {
    private  var _binding: FragmentTopRatedBinding? = null
    private val binding get() = _binding!!
    private lateinit var navCon: NavController
    private lateinit var recyclerView: RecyclerView
    var myDataSource= DataSource().loadDelarationstwo()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.playlistRecycler
        recyclerView.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        recyclerView.adapter= topratedAdapter(requireContext(),myDataSource)

        //        recyclerView Deceorator

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),StaggeredGridLayoutManager.VERTICAL
            )
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bottomNavView: View? = activity?.findViewById(R.id.bottomNavigationView)

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
        _binding = FragmentTopRatedBinding.inflate(inflater, container, false)
        navCon = NavHostFragment.findNavController(this)
        return binding.root
    }

}