package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.kodecamp.gettechie.R


class SettingsFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val privacyBtn: CardView? = activity?.findViewById(R.id.privacy_cardV)
        privacyBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_privacyFragment)
        }
        val shareScreenBtn: CardView? = activity?.findViewById(R.id.share_cardV)
        shareScreenBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_settingsFragment_to_shareScreenFragment)
        }
    }

}