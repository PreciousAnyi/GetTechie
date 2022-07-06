package com.kodecamp.gettechie.activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.ActivityLoginBinding
import com.kodecamp.gettechie.databinding.ActivityMainBinding


const val RC_SIGN_IN=456
class LoginActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val googlelogin=binding.logInButton
        val login=binding.logInAuth
        val check=binding.checkBox
        binding.logInAuth.alpha=.25f
        emailValidationListener()
        passwordValidationListener()
//        what is stored in this sharedpreference should be cleared on signout of the log in account
        val sharedPref:SharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE)
        val checkbox: String? = sharedPref.getString("remember_key","")

        if(checkbox.equals("true")){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        /////////////////////////////////////////////////////////////////////////////////////
//        dont forget sharedPref codes to implement in logout listener for rememeber me to effect the else if
        /////////////////////////////////////////////////////////////////////////////////////
        else if (checkbox.equals("false")){
            val toast = Toast.makeText(this,"Please Sign In", Toast.LENGTH_LONG)
            toast.show()
        }
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)
        val check_log_in: GoogleSignInAccount? =GoogleSignIn.getLastSignedInAccount(this)
        if (check_log_in!=null){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        googlelogin.setOnClickListener {
            val signInIntent = mGoogleSignInClient.getSignInIntent()
            startActivityForResult(signInIntent, RC_SIGN_IN)

        }
        login.setOnClickListener {
           if( binding.logInAuth.alpha==.25f){
               val toast = Toast.makeText(this,"Please Input Sign In Details", Toast.LENGTH_LONG)
               toast.show()
           }
            else{
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
            }
        }


        check.setOnCheckedChangeListener { compoundButton, b ->
            if(compoundButton.isChecked){
                val sharedPref:SharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE)
                val editor:SharedPreferences.Editor=sharedPref.edit()
                editor.putString("remember_key","true")
                editor.apply()
            }

            else if (!compoundButton.isChecked){
                val sharedPref:SharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE)
                val editor:SharedPreferences.Editor=sharedPref.edit()
                editor.putString("remember_key","false")
                editor.apply()
            }
        }
    }

    private fun passwordValidationListener() {
        binding.PasswordEdit.setOnFocusChangeListener { _,focused ->
            if(!focused){
                binding.PasswordContainer.helperText=validPassword()
                validateButton()
            }

        }
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
        return null
    }
    private fun validateButton() {
        val validEmail=binding.EmailContainer.helperText==null
        val validPassword=binding.PasswordContainer.helperText==null
        if(validEmail && validPassword){
            binding.logInAuth.alpha=1f
            binding.logInAuth.isEnabled=true
        }
        else{
            binding.logInAuth.isEnabled=false
        }
    }

    private fun emailValidationListener() {
        binding.EmailEdit.setOnFocusChangeListener { _,focused ->
            if(!focused){
                binding.EmailContainer.helperText=validEmail()
            }
        }
    }

    private fun validEmail(): String? {
        val email=binding.EmailEdit.text.toString()
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            Log.v("bloob","i'm working")

            return "invalid Email Address"
        }
        return null
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//            Log.v("bloob","i'm working")
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {

        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)
         } catch (e: ApiException) {
        }
    }


}