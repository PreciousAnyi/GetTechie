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
import com.kodecamo.gettechie.adapter.courseAdapter
import com.kodecamo.gettechie.adapter.couseoneAdapter
import com.kodecamo.gettechie.adapter.topratedAdapter
import com.kodecamp.gettechie.DataSource
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentCourseBinding
import com.kodecamp.gettechie.databinding.FragmentTopRatedBinding


class courseFragment : Fragment() {
    private  var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!
    private lateinit var navCon: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewone: RecyclerView
    private lateinit var letterId:String
    var myDataSource= DataSource().loadDelarationswebdev()
    var myDataSourceone= DataSource().loadDelarationswebdevtwo()

    companion object{
        //        private const val code: Int = 0
        const val LETTER="letter"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.actionBar?.show()
        arguments?.let{
            letterId=it.getString(LETTER).toString()
        }


        recyclerView = binding.fcourselist
        recyclerViewone = binding.scourselist
        recyclerView.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        if(letterId=="1"){

            recyclerView.adapter= courseAdapter(requireContext(),myDataSource)
        }

        //        recyclerView Deceorator

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),StaggeredGridLayoutManager.VERTICAL
            )
        )

        //        recyclerView Deceorator

        recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),StaggeredGridLayoutManager.VERTICAL
            )
        )
        recyclerViewone.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL)
        recyclerViewone.adapter= couseoneAdapter(requireContext(),myDataSourceone)

        //        recyclerView Deceorator

        recyclerViewone.addItemDecoration(
            DividerItemDecoration(
                requireContext(),StaggeredGridLayoutManager.VERTICAL
            )
        )



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        navCon = NavHostFragment.findNavController(this)
        return binding.root
    }

}