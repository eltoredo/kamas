package com.example.kotlinproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button  = findViewById<Button>(R.id.main_button);
        button.setOnClickListener{
            Toast.makeText(applicationContext, getString(R.string.app_name), Toast.LENGTH_LONG).show()
            intent =  Intent(this, SecondActivity::class.java)
            startActivity(intent)
            //finish() clean the current activity so going back will exit the app
        }
    }
}