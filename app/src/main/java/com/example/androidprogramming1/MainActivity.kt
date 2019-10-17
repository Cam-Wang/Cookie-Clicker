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
import com.example.androidprogramming1.ViewModels.UserViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
    private var counter: Long = 0
    private var numPuddles = 0
    private var numRivers = 0
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
    private  fun updateCounter(count:Long){
        counter = count
        Counter.text = ""+counter
        if(count >= 10)
        {
            titleText.text = "Buy puddles to increase hydration"
            puddleText.visibility = View.VISIBLE
            puddleImage.visibility = View.VISIBLE
        }
        else
        {
            titleText.text = "Click to Hydrate"
            puddleText.visibility = View.INVISIBLE
            puddleImage.visibility = View.INVISIBLE
        }
        if(count >=50)
        {
            riverImage.visibility = View.VISIBLE
            riverText.visibility = View.VISIBLE
        }
        else
        {
            riverImage.visibility = View.INVISIBLE
            riverText.visibility = View.INVISIBLE
        }
    }
    private fun getUsername() = intent.extras?.get("username").toString().toLowerCase(Locale.US)
    private fun getStore() = getPreferences(Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        val countViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
        countViewModel.getUserCount(getUsername()).observe(
            this,
            androidx.lifecycle.Observer { updateCounter(it)  })
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        waterDrop.setOnClickListener{
            counter++
            countViewModel.setUserCount(getUsername(),counter)
            Counter.text = ""+counter
            hydrationText.visibility= View.INVISIBLE

        }
        puddleImage.setOnClickListener{
            if(counter >= 10)
            {
                counter -=10
                Counter.text = ""+counter
                numPuddles++
                puddleText.text = ""+numPuddles
                hydrationText.visibility= View.VISIBLE
                if(counter <= 10)
                {
                    titleText.text = "Click to Hydrate"
                    puddleText.visibility = View.INVISIBLE
                    puddleImage.visibility = View.INVISIBLE
                }
            }
        }

        riverImage.setOnClickListener {
            if(counter>= 50)
            {
                counter -=50
                Counter.text = ""+counter
                numRivers++
                riverText.text = ""+numRivers
                if(counter <= 50)
                {
                    riverImage.visibility = View.INVISIBLE
                    riverText.visibility = View.INVISIBLE
                }
            }
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
