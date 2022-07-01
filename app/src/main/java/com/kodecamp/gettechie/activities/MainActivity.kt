package com.kodecamp.gettechie.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInApi
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.PendingResult
import com.google.android.gms.common.api.ResultCallback
import com.google.android.gms.common.api.Status
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kodecamp.gettechie.R
import kotlin.system.exitProcess
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ///////////////////////////////////////////////////////////
//        logout functionality
//        val text:TextView=findViewById(R.id.text)
//        val gso= GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build()
//        dont forget to clear shared preferences for remember me
//        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//        text.setOnClickListener {
//            mGoogleSignInClient.signOut().addOnCompleteListener(this){
//                val intent = Intent(this, LoginActivity::class.java)
//                startActivity(intent)
//            }
//        }
    }

    var backPressedTime: Long = 0
    override fun onBackPressed() {
        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            showdialog()
        } else {

            Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()

    }

    private fun showdialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Get Techie")
            .setMessage("Do you want to close this application")
            .setCancelable(true)
            .setNegativeButton("YES"){_, _ ->
                finish()
            }
            .show()
    }
    override fun onDestroy() {
        super.onDestroy()
        exitProcess(2)
    }
}

