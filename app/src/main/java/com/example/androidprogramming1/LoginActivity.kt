package com.example.androidprogramming1

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //overrides oncreate function for when the activity starts
        super.onCreate(savedInstanceState) //calling super oncreate
        setContentView(R.layout.activity_login) //sets view

        loginUsernameField.addTextChangedListener(object: TextWatcher{ //sets the input field to update with typed text
            override fun beforeTextChanged(p0: CharSequence?,p1:Int,p2: Int, p3: Int){

            }
            override fun onTextChanged(p0: CharSequence?,p1:Int,p2: Int, p3: Int){

            }
            override fun afterTextChanged(p0: Editable?){

            }


        })

        imageButton.setOnClickListener{startActivity(Intent(this,MainActivity::class.java).apply {putExtra("username",loginUsernameField.text.toString())})}
        //when the login button is clicked passes in the entered username to the main activity
    }
}