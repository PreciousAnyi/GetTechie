package com.kodecamp.gettechie.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isInvisible
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.kodecamo.gettechie.adapter.*
import com.kodecamp.gettechie.DataSource
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentHomeBinding
import com.kodecamp.gettechie.databinding.FragmentWelcomeBinding


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navCon: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewtwo: RecyclerView
    private lateinit var recyclerViewthree: RecyclerView
    var myDataSource= DataSource().loadDelarations()
    var myDataSourcetwo= DataSource().loadDelarationstwo()
    var myDataSourcethree= DataSource().loadDelarationsthree()

    companion object{
        const val LETTER="letter"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val bottom=binding.bottomNavigationView
//        bottom.

        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.seeall.setOnClickListener {
            popularSee()
        }

        binding.topratedlist.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_topRatedFragment)
        }

        binding.recommendedlist.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recommendedFragment)
        }

        binding.popularList.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_popular_playListFragment)
        }


        return binding.root
    }

    private fun popularSee() {
        if(binding.seeall.text=="See all"){
            binding.seeall.text="See less"
            binding.java.isGone=false
            binding.ethical.isGone=false
            binding.graphics.isGone=false
            binding.product.isGone=false
            binding.auto.isGone=false
            binding.cloud.isGone=false
            binding.python.isGone=false
            binding.tech.isGone=false
            binding.digital.isGone=false
        }
        else{
            binding.seeall.text="See all"
            binding.java.isGone=true
            binding.ethical.isGone=true
            binding.graphics.isGone=true
            binding.product.isGone=true
            binding.auto.isGone=true
            binding.cloud.isGone=true
            binding.python.isGone=true
            binding.tech.isGone=true
            binding.digital.isGone=true
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val bottomNavView: BottomNavigationView? = activity?.findViewById(R.id.bottomNavigationView)
        bottomNavView?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.nav_home -> {}
                R.id.nav_explore -> {}
                R.id.nav_downloads -> {}
                R.id.nav_menu -> {findNavController().navigate(R.id.action_homeFragment_to_fullMenu)}
                else -> {
                }

            }
            true
        }
        recyclerView = binding.popularPlaylist
        recyclerView.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL)
        recyclerView.adapter= PlaylistAdapter(requireContext(),myDataSource)

        recyclerViewtwo = binding.topratedPlaylist
        recyclerViewtwo.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL)
        recyclerViewtwo.adapter= homeTopratedAdapter(requireContext(),myDataSourcetwo)

        recyclerViewthree = binding.recommendedPlaylist
        recyclerViewthree.layoutManager= StaggeredGridLayoutManager(1, LinearLayoutManager.HORIZONTAL)
        recyclerViewthree.adapter= homeRecommendedAdapter(requireContext(),myDataSourcethree)

        var hi=binding.username
        var UserName:String=""

        arguments?.let{
            UserName=it.getString(LETTER).toString()
        }

        if(UserName!="null"){

            hi.append(UserName)

        }
        else if(UserName=="null"){
            var hi=binding.username
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val check_log_in: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())
            if (check_log_in != null) {
                val googleUserName= check_log_in?.displayName
                if (googleUserName != null) {
                    hi.append(googleUserName.split(" ")[0])

                }
            }
        }


    }
//

    override fun onDestroy() {
        super.onDestroy()
        activity?.finish()
    }


}