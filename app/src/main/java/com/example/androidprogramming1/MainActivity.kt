package com.example.androidprogramming1

import android.content.Context
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





class MainActivity : AppCompatActivity() {
    private var counter: Long = 0;
    private var numPuddles = 0;
    private var numRivers = 0;
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
    fun getStore() = getPreferences(Context.MODE_PRIVATE)
    override fun onCreate(savedInstanceState: Bundle?) {
        var user = intent.extras?.getString("username","default")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
            counter = savedInstanceState.getLong(user,0)
            Counter.text = "" + counter
            if(counter >= 10){
                titleText.text = "You are hydrated!"
                puddleText.visibility = View.VISIBLE
                puddleImage.visibility = View.VISIBLE
            }
        }
        else    {
            counter = getStore().getLong(user,0)
            Counter.text = ""+counter
        }
        waterDrop.setOnClickListener{
            counter++
            Counter.text = ""+counter
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
        puddleImage.setOnClickListener{
            if(counter >= 10)
            {
                counter -=10
                Counter.text = ""+counter
                numRivers++
                riverText.text = ""+numRivers
            }
        }

        riverImage.setOnClickListener {
            if(counter>= 50)
            {
                counter -=50
                Counter.text = ""+counter
                numRivers++
                riverText.text = ""+numRivers
            }
        }


    }
    override  fun onPause()
    {
        var user = intent.extras?.getString("username","default")
        super.onPause()
        getStore().edit().putLong(user,counter).apply()
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.run {
            var user = intent.extras?.getString("username","default")
            putLong(user,counter)
        }
    }
    companion object {
        private const val CounterKey = "counterKey"
    }

}
