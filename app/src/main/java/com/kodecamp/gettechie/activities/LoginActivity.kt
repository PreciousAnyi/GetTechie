package com.kodecamp.gettechie.activities

import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.util.Base64
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.GraphRequest
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kodecamp.gettechie.R
import org.json.JSONObject
import java.lang.Exception
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException


const val RC_SIGN_IN=456
class LoginActivity : AppCompatActivity() {
    @Suppress("DEPRECATION")
    lateinit var callbackManager: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val googlelogin: ImageView = findViewById(R.id.log_in_button)
        val login:Button=findViewById(R.id.log_in_auth)
        val check:CheckBox=findViewById(R.id.checkBox)
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
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
        val loginButton : ImageView = findViewById(R.id.fbLogin_button)
               loginButton.setOnClickListener {
                        loginWithFacebook()
               }
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
                LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
                        override fun onSuccess(result: LoginResult) {
                                result.let {
                                        val intent = Intent(applicationContext, MainActivity::class.java)
                                    startActivity(intent)
                                    val graphRequest = GraphRequest.newMeRequest(result?.accessToken){ `object`, response ->
                                            getFacebookData(`object`)
                                        }
                                    val parameters = Bundle()
                                    parameters.putString("fields","email")
                                    graphRequest.parameters = parameters
                                    graphRequest.executeAsync()
                                    }
                            }

                        override fun onCancel() {
                                Toast.makeText(this@LoginActivity, "Facebook login cancelled", Toast.LENGTH_SHORT).show()
                            }

                     override fun onError(error: FacebookException) {
                                Toast.makeText(this@LoginActivity, "Facebook login failed: ${error.toString()}", Toast.LENGTH_SHORT).show()
                            }

                     })
        }
}