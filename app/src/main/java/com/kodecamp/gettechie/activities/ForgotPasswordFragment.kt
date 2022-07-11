package com.kodecamp.gettechie.activities

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText
import com.kodecamp.gettechie.databinding.FragmentForgotPasswordBinding


class ForgotPasswordFragment : Fragment() {

    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var continueBtn: Button
    private lateinit var emailEditText: TextInputEditText
    private lateinit var forgotPasswordEmail: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        /*val view = inflater.inflate(R.layout.fragment_forgot_password, container, false)*/

        binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)

        //setting button opacity and functionality
        binding.continueBtn.alpha = .25f
        binding.continueBtn.isEnabled = false

        //this checks the validity of the email
        emailValidationListener()


        return binding.root
    }

    private fun emailValidationListener() {

        binding.EmailInputEditText.editText?.doOnTextChanged { _, _, _, _ ->
            binding.EmailInputEditText.helperText = validEmail()
            validateButton()
        }
    }

    private fun validateButton() {
        val validEmail = binding.EmailInputEditText.helperText == null
        if (validEmail) {
            binding.continueBtn.alpha = 1f
            binding.continueBtn.isEnabled = true
        }
    }

    private fun validEmail(): String? {
        val email = binding.forgotPasswordEmail.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.v("bloob", "i'm working")

            return "invalid Email Address"
        }
        return null

    }


}