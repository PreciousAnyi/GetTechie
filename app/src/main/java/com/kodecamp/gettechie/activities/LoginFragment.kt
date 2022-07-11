package com.kodecamp.gettechie.activities

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import org.json.JSONObject


const val RC_SIGN_IN = 456

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    lateinit var callbackManager: CallbackManager
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        val googlelogin = binding.logInButton
        val login = binding.logInAuth
        val check = binding.checkBox
        binding.logInAuth.alpha = .25f
        emailValidationListener()
        passwordValidationListener()


        //on textview clicked it takes you from this fragment to the forgot password fragment
        binding.textView4.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)

        }

//        what is stored in this sharedpreference should be cleared on signout of the log in account
        val sharedPref =
            activity?.getSharedPreferences("checkbox", MODE_PRIVATE)
        val checkbox: String? = sharedPref?.getString("remember_key", "")

        if (checkbox.equals("true")) {
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
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
            val mGoogleSignInClient = GoogleSignIn.getClient(activity!!, gso)
            val check_log_in: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(activity!!)
            if (check_log_in != null) {
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
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
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
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


    }

    private fun passwordValidationListener() {
        binding.PasswordEdit.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.PasswordContainer.helperText = validPassword()
                validateButton()
            }

        }
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
        val validEmail = binding.EmailContainer.helperText == null
        val validPassword = binding.PasswordContainer.helperText == null
        if (validEmail && validPassword) {
            binding.logInAuth.alpha = 1f
            binding.logInAuth.isEnabled = true
        } else {
            binding.logInAuth.isEnabled = false
        }
    }

    private fun emailValidationListener() {
        binding.EmailEdit.setOnFocusChangeListener { _, focused ->
            if (!focused) {
                binding.EmailContainer.helperText = validEmail()
            }
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
            val intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
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
                        val intent = Intent(context, MainActivity::class.java)
                        startActivity(intent)
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