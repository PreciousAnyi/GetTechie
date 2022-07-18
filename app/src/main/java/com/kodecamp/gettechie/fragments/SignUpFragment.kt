package com.kodecamp.gettechie.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentSignUpBinding
import com.kodecamp.gettechie.viewmodels.SignUpViewModel
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {

    private  var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var navCon: NavController
    private val viewModel by viewModels<SignUpViewModel>()

    companion object{
        const val RC_SIGN_IN = 456
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        navCon = NavHostFragment.findNavController(this)

        binding.logInAuth.alpha=.25f
        val check = binding.checkBox
        val login=binding.logInAuth


        binding.mylogin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }


        val sharedPref =
            activity?.getSharedPreferences("checkbox", Context.MODE_PRIVATE)
        val checkbox: String? = sharedPref?.getString("remember_key", "")

        if (checkbox.equals("true")) {
            findNavController().navigate(R.id.action_signUpFragment_to_welcomeFragment)
        }


        binding.EmailEdit.doOnTextChanged { text, start, before, count ->
            binding.EmailContainer.error=validEmail()
            validateButton()
        }


        binding.NameEdit.doOnTextChanged { text, start, before, count ->
            binding.NameContainer.error=validFullName()
            validateButton()
        }


        binding.PasswordEdit.doOnTextChanged { text, start, before, count ->
            binding.PasswordContainer.error=validPassword()
            validateButton()
        }


        binding.ConfirmPasswordEdit.doOnTextChanged { text, start, before, count ->
            binding.ConfirmPasswordContainer.error=validConfirmPassword()
            validateButton()
        }


        check.setOnCheckedChangeListener { compoundButton, b ->
            activity?.getSharedPreferences("checkbox", Context.MODE_PRIVATE)?.edit()?.run {

                putString("remember_key", compoundButton.isChecked.toString())
                apply()
            }
        }


        if (activity != null) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
            val check_log_in: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())
            if (check_log_in != null) {
                val UserName=check_log_in.givenName
                Log.v("test1",UserName.toString())
                findNavController().navigate(R.id.action_signUpFragment_to_welcomeFragment)
            }
            binding.logInButton.setOnClickListener {
                val signInIntent = mGoogleSignInClient.getSignInIntent()
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }
        login.setOnClickListener {
            var letters:String=""
            var fullName=binding.NameEdit.text.toString()
            if(fullName.contains(" ")){
                letters=fullName.split(" ")[0]
//                var letters=
            }
            else{
                letters=fullName
            }

            var actionn=SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment(letter = letters)
            if( binding.logInAuth.alpha==.25f){
                val toast = Toast.makeText(requireContext(),"Please Input Sign Up Details", Toast.LENGTH_LONG)
                toast.show()
            }
            else{
                login.findNavController().navigate(actionn)
//                findNavController().navigate(R.id.action_signUpFragment_to_welcomeFragment)
            }
        }
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        var actionn=SignUpFragmentDirections.actionSignUpFragmentToWelcomeFragment(letter = letters)
//
//    }

    private fun validateButton() {
        val validEmail=binding.EmailContainer.error==null
        val validPassword=binding.ConfirmPasswordContainer.error==null
        val validFullName=binding.NameContainer.error==null
        if(validEmail && validPassword && validFullName){
            binding.logInAuth.alpha=1f
            binding.logInAuth.isEnabled=true
        }
        else{
            binding.logInAuth.isEnabled=false
        }
    }


    private fun validConfirmPassword(): String? {
        val cpassword=binding.ConfirmPasswordEdit.text.toString()
        if (cpassword!=binding.PasswordEdit.text.toString()){
            return "Your password does not match"
        }
        else {
            return null
        }
    }

    private fun validFullName(): String? {
        val FullName=binding.NameEdit.text.toString()

        if (FullName.isEmpty()){
            return "Field cannot be empty"
        }
        if(!FullName[0].isUpperCase()){
            return "full name must start with Upper Case"
        }
        if (FullName.matches(".*[0-9].*".toRegex())){
            return "Your full must not contain numbers"
        }
        if (FullName.matches(".*[!@#$%^&*()_+-={}|\"':;>.?/<,~`].*".toRegex())){
            return "Invalid format "
        }
        return null
    }
    private fun validPassword(): String? {
        val password=binding.PasswordEdit.text.toString()
        if (password.length<8){
            return "Minimum 8 Character Password"
        }
        if (!password.matches(".*[A-Z].*".toRegex())){
            return "Your password must include atleast one capital letter"
        }
        if (!password.matches(".*[a-z].*".toRegex())){
            return "Your password must include atleast one small letter"
        }
        if (!password.matches(".*[$#@^&()_!,;:=+.].*".toRegex())){
            return "Your password must include atleast one special character"
        }
        else{
            return null

        }
    }

    private fun validEmail(): String? {
        val email=binding.EmailEdit.text.toString()
        if (email.isEmpty()){
            return "Field cannot be empty"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            return "Email address is incorrect"
        }

        return null
    }
    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
            findNavController().navigate(R.id.action_signUpFragment_to_welcomeFragment)
//            Log.v("bloob","i'm working")
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
        } catch (e: ApiException) {
        }
    }

    override fun onResume() {
        lifecycleScope.launch {
            launch {
                viewModel.email.collect {
                    binding.EmailEdit.setText(it)
                }
            }
            launch {
                viewModel.password.collect {
                    binding.PasswordEdit.setText(it)
                }
            }
            launch {
                viewModel.confirmPassword.collect {
                    binding.ConfirmPasswordEdit.setText(it)
                }
            }
            launch {
                viewModel.name.collect {
                    binding.NameEdit.setText(it)
                }
            }
            super.onResume()
        }

    }

    override fun onPause() {
        super.onPause()
        viewModel.updateName(binding.NameEdit.text.toString())
        viewModel.updateEmail(binding.EmailEdit.text.toString())
        viewModel.updatePassword(binding.PasswordEdit.text.toString())
        viewModel.updateConfirmPassword(binding.ConfirmPasswordEdit.text.toString())
    }
}