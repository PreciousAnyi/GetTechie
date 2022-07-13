package com.kodecamp.gettechie.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kodecamp.gettechie.R
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_GetTechie)
        setContentView(R.layout.activity_main)
//        val theText: TextView = findViewById(R.id.text)
//        theText.setOnClickListener {
//            setContentView(R.layout.create_password)
//        }
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
            show_dialog()
        } else {
            Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG).show()
        }
        backPressedTime = System.currentTimeMillis()

    }

    private fun show_dialog() {
        MaterialAlertDialogBuilder(this, R.style.AlertDialogTheme)
            .setTitle("Get Techie")
            .setMessage("Do you want to close this application.")
            .setPositiveButton(
                "YES"
            ) { dialogInterface, i ->
                finish()
            }
            .setNeutralButton(
                "CANCEL"
            ) { dialogInterface, i -> }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        exitProcess(2)
    }
}

