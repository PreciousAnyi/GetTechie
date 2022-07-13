package com.kodecamp.gettechie.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.google.android.material.textfield.TextInputEditText
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentForgotPasswordBinding
import com.kodecamp.gettechie.viewmodels.ForgotPasswordViewModel
import kotlinx.coroutines.launch


class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var continueBtn: Button
    private lateinit var emailEditText: TextInputEditText
    private lateinit var forgotPasswordEmail: EditText
    private val viewModel by navGraphViewModels<ForgotPasswordViewModel>(R.id.forgot_password_nav)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        //setting button opacity and functionality
        binding.continueBtn.alpha = .25f
        binding.continueBtn.setOnClickListener {
            if( binding.continueBtn.alpha==.25f){
                val toast = Toast.makeText(requireContext(),"Please Input Sign Up Details", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                findNavController().navigate(R.id.action_forgotPasswordFragment_to_resetPasswordFragment)
            }
        }
        //this checks the validity of the email
        binding.forgotPasswordEmail.doOnTextChanged { text, start, before, count ->
            binding.emailContainer.error=validEmail()
            validateButton()
        }

        return binding.root
    }


    private fun validateButton() {
        val validEmail = binding.emailContainer.error == null
        if (validEmail) {
            binding.continueBtn.alpha = 1f
            binding.continueBtn.isEnabled = true
        }
        else{
            binding.continueBtn.isEnabled=false
        }
    }

    private fun validEmail(): String? {
        val email = binding.forgotPasswordEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            Log.v("bloob", "i'm working")

            return "invalid Email Address"
        }
        return null

    }

    override fun onResume() {
        lifecycleScope.launch {
            launch {
                viewModel.email.collect {
                    binding.forgotPasswordEmail.setText(it)
                }
            }
        }
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateEmail(binding.forgotPasswordEmail.text.toString())
    }


}