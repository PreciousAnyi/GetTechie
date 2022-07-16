package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navGraphViewModels
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentCreatePasswordBinding
import com.kodecamp.gettechie.viewmodels.ForgotPasswordViewModel
import kotlinx.coroutines.launch

class createPasswordFragment : Fragment() {
    private lateinit var binding: FragmentCreatePasswordBinding
    private val viewModel by navGraphViewModels<ForgotPasswordViewModel>(R.id.createPasswordFragment)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreatePasswordBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onResume() {
        lifecycleScope.launch {
            launch {
                viewModel.password.collect {
                    binding.createNewPasswordEdit.setText(it)
                }
            }
            launch {
                viewModel.confirmPassword.collect {
                    binding.confirmNewPasswordEdit.setText(it)
                }
            }
        }
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.updatePassword(binding.createNewPasswordEdit.text.toString())
        viewModel.updateConfirmPassword(binding.confirmNewPasswordEdit.text.toString())
    }

}