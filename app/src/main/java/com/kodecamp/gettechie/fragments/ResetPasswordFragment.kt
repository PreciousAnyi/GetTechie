package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentResetPasswordBinding
import com.kodecamp.gettechie.viewmodels.ForgotPasswordViewModel
import kotlinx.coroutines.launch


class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private val viewModel by navGraphViewModels<ForgotPasswordViewModel>(R.id.resetPasswordFragment)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentResetPasswordBinding.inflate(inflater, container, false)

        binding.verifyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_createPasswordFragment)
        }

        return binding.root
    }

    override fun onResume() {
        lifecycleScope.launch {
            launch {
                viewModel.otp1.collect {
                    binding.otpTv1.setText(it)
                }
            }
            launch {
                viewModel.otp2.collect {
                    binding.otpTv2.setText(it)
                }
            }
            launch {
                viewModel.otp3.collect {
                    binding.otpTv3.setText(it)
                }
            }
            launch {
                viewModel.otp4.collect {
                    binding.otpTv4.setText(it)
                }
            }
        }
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateOtp1(binding.otpTv1.text.toString())
        viewModel.updateOtp2(binding.otpTv2.text.toString())
        viewModel.updateOtp3(binding.otpTv3.text.toString())
        viewModel.updateOtp4(binding.otpTv4.text.toString())
    }


}