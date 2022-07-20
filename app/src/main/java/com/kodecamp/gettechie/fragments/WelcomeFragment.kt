package com.kodecamp.gettechie.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.graphics.red
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kodecamp.gettechie.R
import com.kodecamp.gettechie.databinding.FragmentSignUpBinding
import com.kodecamp.gettechie.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding

    private lateinit var UserName:String
    var clickedList= MutableList(0){""}
    var clicked=0

    companion object{
        const val LETTER="letter"
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        binding.continueButton.alpha =.25f



        binding.continueButton.setOnClickListener {
            validateButton()
        }


        binding.auto.setOnClickListener {
                    myClick(binding.auto)

        }


        binding.button1.setOnClickListener {
            myClick(binding.button1)

        }

        binding.dataAnalysis.setOnClickListener {

            myClick(binding.dataAnalysis)

        }

        binding.dataStructure.setOnClickListener {

            myClick(binding.dataStructure)

        }

        binding.java.setOnClickListener {

            myClick(binding.java)

        }

        binding.ethical.setOnClickListener {

            myClick(binding.ethical)

        }

        binding.graphics.setOnClickListener {

            myClick(binding.graphics)

        }
        binding.web.setOnClickListener {

            myClick(binding.web)

        }

        binding.word.setOnClickListener {

            myClick(binding.word)

        }
        binding.product.setOnClickListener {

            myClick(binding.product)

        }
        binding.cloud.setOnClickListener {

            myClick(binding.cloud)

        }
        binding.net.setOnClickListener {

            myClick(binding.net)

        }
        binding.python.setOnClickListener {

            myClick(binding.python)


        }
        binding.tech.setOnClickListener {

            myClick(binding.tech)


        }
        binding.digital.setOnClickListener {

            myClick(binding.digital)

        }


        return binding.root


    }

    fun myClick(v:Button) {
        if (v.currentTextColor == Color.BLACK) {
            if(!clickedList.contains(v.text.toString())){
                clickedList.add(v.text.toString())
            }
            v.setTextColor(Color.WHITE)
            v.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.blue)
            continueColor()
        }
        else if(v.currentTextColor == Color.WHITE) {
            if(clickedList.contains(v.text.toString())){
                clickedList.remove(v.text.toString())
            }
            v.setTextColor(Color.BLACK)
            v.backgroundTintList =
                ContextCompat.getColorStateList(requireContext(), R.color.white)
            continueColor()
        }

    }

    private fun continueColor() {
        if(clickedList.isEmpty()){
            binding.continueButton.alpha =.25f
        }
        else if (clickedList.isNotEmpty()){
            binding.continueButton.alpha =1f
        }
    }
    private fun show_dialog() {
        MaterialAlertDialogBuilder(requireContext(), R.style.AlertDialogTheme)
            .setTitle("Congratulations!")
//            .setIcon(R.drawable.mark)
            .setMessage("Your account has been set up. Have a great learning time.")
            .setNegativeButton(
                "Back to Home"
            ) { dialogInterface, i ->
                var actionn=WelcomeFragmentDirections.actionWelcomeFragmentToHomeFragment(letter = binding.textView2.text.toString())
                binding.continueButton.findNavController().navigate(actionn)
            }
            .show()
    }

    private fun validateButton() {
//        Log.v("test2",clickedList.toString())
        if(clickedList.isEmpty()){
            val toast =
                Toast.makeText(context, "Please Select Objects of Interest", Toast.LENGTH_LONG)
            toast.show()
        }
        else{

            show_dialog()

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var hi=binding.textView2

        arguments?.let{
            UserName=it.getString(LETTER).toString()
        }

        if(UserName!="null"){

            hi.append(","+" "+ UserName)

        }
        else if(UserName=="null"){
            var hi=binding.textView2
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()
            val check_log_in: GoogleSignInAccount? = GoogleSignIn.getLastSignedInAccount(requireContext())
            if (check_log_in != null) {
                val googleUserName= check_log_in?.displayName
                if (googleUserName != null) {
                    hi.append(","+" "+ googleUserName.split(" ")[0])

                }
            }
        }

//

    }


}