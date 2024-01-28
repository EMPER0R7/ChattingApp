package com.example.bolbhai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        val inname = findViewById<TextInputEditText>(R.id.name)
        val inemail = findViewById<TextInputEditText>(R.id.email)
        val inpassword = findViewById<TextInputEditText>(R.id.password)
        val btnsgn = findViewById<Button>(R.id.button2)

        btnsgn.setOnClickListener {
            val name = inname.text.toString()
            val email = inemail.text.toString()
            val password = inpassword.text.toString()
            val users = Users(name, email, password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(users).addOnSuccessListener {
                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }
        val gotologin = findViewById<TextView>(R.id.goToLogin)
        gotologin.setOnClickListener {
            val glogin = Intent(this, login::class.java)
            startActivity(glogin)
        }


    }

}