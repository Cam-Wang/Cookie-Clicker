package com.example.androidprogramming1

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.SharedPreferences
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import com.example.androidprogramming1.util.toggleVisibility
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.androidprogramming1.ViewModels.UserViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private var counter: Long = 0 //create the counter variable to store user score
    private var numPuddles = 0 //stores number of puddles owned by user (currently not saved)
    private var numRivers = 0//stores number of rivers owned by user (currently not saved)
    /*
    private val handler = Handler()
    private val runnable = object : Runnable {
        override fun run() {
            /* do what you need to do */
            /* and here comes the "trick" */
            counter += (10 * numPuddles)
            handler.postDelayed(this, 100)
        }
    }

     */
    private  fun updateCounter(count:Long){ //updateCounter function used with livedata
        counter = count //setting score from livedata
        Counter.text = ""+counter //update the screen text
        if(count >= 10) //check to display the puddle image and text
        {
            titleText.text = "Buy puddles to increase hydration"
            puddleText.visibility = View.VISIBLE
            puddleImage.visibility = View.VISIBLE
        }
        else //checks to not display the puddle image and text
        {
            titleText.text = "Click to Hydrate"
            puddleText.visibility = View.INVISIBLE
            puddleImage.visibility = View.INVISIBLE
        }
        if(count >=50) //check to display the river image and text
        {
            riverImage.visibility = View.VISIBLE
            riverText.visibility = View.VISIBLE
        }
        else //check to not display the river image and text
        {
            riverImage.visibility = View.INVISIBLE
            riverText.visibility = View.INVISIBLE
        }
    }
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US) //getting username from login page
    //private fun getStore() = getPreferences(Context.MODE_PRIVATE) //getting store unused
    override fun onCreate(savedInstanceState: Bundle?) { //override the activities oncreate function called at the beginning
        val countViewModel = ViewModelProviders.of(this)[UserViewModel::class.java] //getting a viewmodel using the default constructor
        countViewModel.getUserCount(getUsername()).observe( //setting to observe the counter and setting to use it with the updateCounter function
            this,
            androidx.lifecycle.Observer { updateCounter(it)  })
        super.onCreate(savedInstanceState)
        val gif = countViewModel.getGif().observe(
            this,
            androidx.lifecycle.Observer {
                Glide.with(this)
                .load(it.url)
                .into(myImage)  })

        setContentView(R.layout.activity_main) //setting the view for the activity to R.layout
    /*
        if(savedInstanceState != null){
            counter = savedInstanceState.getLong(user,0)
            Counter.text = "" + counter
            if(counter >= 10){
                titleText.text = "Buy puddles to increase hydration"
                puddleText.visibility = View.VISIBLE
                puddleImage.visibility = View.VISIBLE
            }
            if(counter >= 50){
                riverImage.visibility = View.VISIBLE
                riverText.visibility = View.VISIBLE
            }
        }
        else {
            counter = getStore().getLong(user, 0)
            Counter.text = "" + counter

            if (counter >= 10) {
                titleText.text = "Buy puddles to increase hydration"
                puddleText.visibility = View.VISIBLE
                puddleImage.visibility = View.VISIBLE
            }
            if (counter >= 50) {
                riverImage.visibility = View.VISIBLE
                riverText.visibility = View.VISIBLE
            }
        }
        */

        //setting the waterdrop image to be a button
        waterDrop.setOnClickListener{
            counter++ //increments counter
            countViewModel.setUserCount(getUsername(),counter) //updates the livedata with the incremented counter
            Counter.text = ""+counter //update text
            hydrationText.visibility= View.INVISIBLE //changes the hydration text

        }
        //set the puddle image to a button
        puddleImage.setOnClickListener{
            if(counter >= 10) //checks that the currency is more than the purchase price
            {
                counter -=10 //substracts the price from the score
                Counter.text = ""+counter //updates score
                numPuddles++ //increments the number of puddles
                puddleText.text = ""+numPuddles //updates the text for the puddles
                hydrationText.visibility= View.VISIBLE //shows the title text with updated text

                if(counter <= 10) //checks if the counter post interaction is less than 10
                {
                    titleText.text = "Click to Hydrate" //updates the title text
                    puddleText.visibility = View.INVISIBLE //disables puddle text
                    puddleImage.visibility = View.INVISIBLE //disables puddle image
                }


            }
        }
        //sets river image to be a button
        riverImage.setOnClickListener {
            /*if(counter>= 50) //checks if user has enough to buy a river
            {
                counter -=50 //substacts cost of a river
                Counter.text = ""+counter //updates counter text
                numRivers++ //increments river
                riverText.text = ""+numRivers //updates numrivers text

                if(counter <= 50) //checks if after purchase if the river hsould still be there
                {
                    riverImage.visibility = View.INVISIBLE //hides river image
                    riverText.visibility = View.INVISIBLE //hides river text
                }
            */
        }


    }
    /*
    override  fun onPause()
    {
        val user = intent.extras?.getString("username","default")
        super.onPause()
        getStore().edit().putLong(user,counter).apply()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.run {
            val user = intent.extras?.getString("username","default")
            putLong(user,counter)
        }
    }
     */

}
