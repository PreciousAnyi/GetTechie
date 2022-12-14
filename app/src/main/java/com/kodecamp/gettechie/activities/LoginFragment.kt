package com.kodecamp.gettechie.activities

import android.content.Context.MODE_PRIVATE
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
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentLoginBinding
import com.kodecamp.gettechie.viewmodels.LoginViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject



class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    lateinit var callbackManager: CallbackManager

    companion object{
        const val RC_SIGN_IN = 456
    }
    //adding viewModel instance
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val googlelogin = binding.logInButton
        val login = binding.logInAuth
        val check = binding.checkBox
        binding.logInAuth.alpha = .25f
        binding.signup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
//        on text change in the edit text it starts validating
        binding.EmailEdit.doOnTextChanged { text, start, before, count ->
            binding.EmailContainer.error=validEmail()
            validateButton()
        }
        binding.PasswordEdit.doOnTextChanged { text, start, before, count ->
            binding.PasswordContainer.error=validPassword()
            validateButton()

        }
        //on textview clicked it takes you from this fragment to the forgot password fragment
        binding.forgot.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)

        }

//        what is stored in this sharedpreference should be cleared on signout of the log in account
        val sharedPref =
            activity?.getSharedPreferences("checkbox", MODE_PRIVATE)
        val checkbox: String? = sharedPref?.getString("remember_key", "")

        if (checkbox.equals("true")) {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
        /////////////////////////////////////////////////////////////////////////////////////
//        dont forget sharedPref codes to implement in logout listener for rememeber me to effect the else if
        /////////////////////////////////////////////////////////////////////////////////////
        else if (checkbox.equals("false")) {
            val toast = Toast.makeText(context, "Please Sign In", Toast.LENGTH_LONG)
            toast.show()
        }

        if (activity != null) {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
            val check_log_in: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())
            if (check_log_in != null) {
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
            googlelogin.setOnClickListener {
                val signInIntent = mGoogleSignInClient.getSignInIntent()
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
        }

        login.setOnClickListener {
            if (binding.logInAuth.alpha == .25f) {
                val toast =
                    Toast.makeText(context, "Please Input Sign In Details", Toast.LENGTH_LONG)
                toast.show()
            } else {
                findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
            }
        }


        check.setOnCheckedChangeListener { compoundButton, b ->
            activity?.getSharedPreferences("checkbox", MODE_PRIVATE)?.edit()?.run {

                putString("remember_key", compoundButton.isChecked.toString())
                apply()
            }
        }
        binding.fbLoginButton.setOnClickListener {
            loginWithFacebook()
        }
        return binding.root


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
        }
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.updateEmail(binding.EmailEdit.text.toString())
        viewModel.updatePassword(binding.PasswordEdit.text.toString())
    }


    private fun validPassword(): String? {
        val password = binding.PasswordEdit.text.toString()
        if (password.length < 8) {
            return "Minimum 8 Character Password"
        }
        if (!password.matches(".*[A-Z].*".toRegex())) {
            return "Your password must include atleast one capital letter"
        }
        if (!password.matches(".*[a-z].*".toRegex())) {
            return "Your password must include atleast one small letter"
        }
        if (!password.matches(".*[$#@^&()_!,;:=+.].*".toRegex())) {
            return "Your password must include atleast one special character"
        }
        return null
    }

    private fun validateButton() {
        val validEmail = binding.EmailContainer.error == null
        val validPassword = binding.PasswordContainer.error == null
        if (validEmail && validPassword) {
            binding.logInAuth.alpha = 1f
            binding.logInAuth.isEnabled = true
        } else {
            binding.logInAuth.isEnabled = false
        }
    }


    private fun validEmail(): String? {
        val email = binding.EmailEdit.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Log.v("bloob", "i'm working")

            return "invalid Email Address"
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
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
//            Log.v("bloob","i'm working")
        }
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
        } catch (e: ApiException) {
        }
    }

    private fun getFacebookData(obj: JSONObject?) {
        val email = obj?.getString("email")
        //        val theEmail : TextView = findViewById(R.id.mainTextTv)
        //        theEmail.text = "EMAIL: ${email}"
    }

    private fun loginWithFacebook() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().logInWithReadPermissions(this, setOf("email"))
        LoginManager.getInstance()
            .registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                override fun onSuccess(result: LoginResult) {
                    result.let {
                        findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
//                        val intent = Intent(context, MainActivity::class.java)
//                        startActivity(intent)

//                        val fragTransaction = fragmentManager?.beginTransaction()
//                        fragTransaction.replace(R.id.)

                        val graphRequest =
                            GraphRequest.newMeRequest(result?.accessToken) { `object`, response ->
                                getFacebookData(`object`)
                            }
                        val parameters = Bundle()
                        parameters.putString("fields", "email")
                        graphRequest.parameters = parameters
                        graphRequest.executeAsync()
                    }
                }

                override fun onCancel() {
                    Toast.makeText(
                        context,
                        "Facebook login cancelled",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onError(error: FacebookException) {
                    Toast.makeText(
                        context,
                        "Facebook login failed: ${error.toString()}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            })
    }
}