package com.rick.homework3_1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.rick.homework3_1.databinding.ActivityMaterialBinding

class MaterialActivity : AppCompatActivity() {
    val secretNumber = SecretNumber()

    val TAG = MaterialActivity ::class.java.simpleName

    lateinit var edNumber : EditText
    lateinit var textCounter : TextView

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMaterialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMaterialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "secret: " + secretNumber.secret)
        findViews()
        textCounter.setText(secretNumber.count.toString())

        setSupportActionBar(binding.toolbar)

        /*val navController = findNavController(R.id.nav_host_fragment_content_material)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/

        binding.fab.setOnClickListener { view ->
            /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()*/
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.replay_game))
                .setMessage(getString(R.string.are_you_sure))
                .setPositiveButton(getString(R.string.ok), {dialog, which ->
                    secretNumber.reset()
                    textCounter.setText(secretNumber.count.toString())
                    edNumber.setText("")
                })
                .setNeutralButton("Cancel", null)
                .show()
        }
    }

    private fun findViews() {
        edNumber = findViewById(R.id.ed_number)
        textCounter = findViewById(R.id.text_counter)
    }

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_material)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/

    fun check(view: View){
        val n = edNumber.text.toString().toInt()
        val diff = secretNumber.validate(n)
        var count = secretNumber.count
        var message = getString(R.string.excellent_the_number_is)
        val message2 = getString(R.string.game_over)

        if (count <= 3 && diff != 0){
            Log.d(TAG, "number:" +n)
            if (diff < 0) {
                message = getString(R.string.bigger)
            } else if (diff > 0) {
                message = getString(R.string.smaller)
            }
            textCounter.setText(secretNumber.count.toString())  //每猜一次之後count加1，更新畫面次數

            if (count == 3) {
                AlertDialog.Builder(this)
                    .setTitle(message2)
                    .setMessage(message2)
                    .setPositiveButton(getString(R.string.ok), { dialog, which ->
                        secretNumber.reset()
                        textCounter.setText(secretNumber.count.toString())
                        edNumber.setText("")
                    })
                    .setNeutralButton(getString(R.string.cancel), null)
                    .show()
            }

            AlertDialog.Builder(this)
                .setTitle(getString(R.string.message))
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), null)
                .show()
        }

        if (count <= 3 && diff == 0){
            message = "$message ${secretNumber.secret}"
            AlertDialog.Builder(this)
                .setTitle(message2)
                .setMessage(message)
                .setPositiveButton(getString(R.string.ok), { dialog, which ->
                    secretNumber.reset()
                    textCounter.setText(secretNumber.count.toString())
                    edNumber.setText("")
                })
                .setNeutralButton(getString(R.string.cancel), null)
                .show()
        }
    }
}