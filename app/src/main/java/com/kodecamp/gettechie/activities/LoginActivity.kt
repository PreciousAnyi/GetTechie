package com.kodecamp.gettechie.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.kodecamp.gettechie.R


const val RC_SIGN_IN=456
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val google_login: ImageView = findViewById(R.id.log_in_button)
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google_login.setOnClickListener {
            val signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task= GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
            val intent = Intent(this, OnboardingActivity::class.java)
            startActivity(intent)
            Log.v("bloob","i'm working")
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account: GoogleSignInAccount = completedTask.getResult(ApiException::class.java)

//            user_name.text=account.displayName
            // Signed in successfully, show authenticated UI.
//            val myIntent = Intent(this, IntroFRagment::class.java)
//            startActivity(myIntent)
//            finish()
         } catch (e: ApiException) {
        }
    }
}