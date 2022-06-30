package com.kodecamp.gettechie.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
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


const val RC_SIGN_IN=456
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val google_login: ImageView = findViewById(R.id.log_in_button)
        val log_in:Button=findViewById(R.id.log_in_auth)
        val check:CheckBox=findViewById(R.id.checkBox)
        val sharedPref:SharedPreferences = getSharedPreferences("checkbox",MODE_PRIVATE)
        val checkbox: String? = sharedPref.getString("remember_key","")
        if(checkbox.equals("true")){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        else if (checkbox.equals("false")){
            var toast = Toast.makeText(this,"Please Sign In", Toast.LENGTH_LONG)
            toast.show()
        }
        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google_login.setOnClickListener {
            val signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);

        }
        log_in.setOnClickListener {
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
    }
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