package com.rick.homework3_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()
    val TAG = MainActivity ::class.java.simpleName

    lateinit var edNumber : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "secret: " + secretNumber.secret)
        findViews()
    }

    private fun findViews() {
        edNumber = findViewById(R.id.ed_number)
    }

    fun check(view: View){
        val n = edNumber.text.toString().toInt()
        val diff = secretNumber.validate(n)
        var message = getString(R.string.excellent_the_number_is)

//        println("number: $n")
        Log.d(TAG, "number:" +n)
        if (diff < 0){
            message = getString(R.string.bigger)
        }else if (diff > 0){
            message = getString(R.string.smaller)
        }else{
            message = "$message ${secretNumber.secret}"
        }
//        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.message))
            .setMessage(message)
            .setPositiveButton(getString(R.string.ok), null)
            .show()
    }
}