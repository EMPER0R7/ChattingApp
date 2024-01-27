package com.example.bolbhai

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val name=findViewById<TextInputEditText>(R.id.name)
        val email=findViewById<TextInputEditText>(R.id.email)
        val password =findViewById<TextInputEditText>(R.id.password)
        val btnlgn=findViewById<Button>(R.id.button2)


    }
}