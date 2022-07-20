package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.editProfileButton.setOnClickListener {
//            findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }


        return binding.root
    }


}