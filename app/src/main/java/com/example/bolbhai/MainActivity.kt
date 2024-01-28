package com.example.bolbhai

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DatabaseReference

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
//            val users = Users(name, email, password)
//            database = FirebaseDatabase.getInstance().getReference("Users")
//            database.child(name).setValue(users).addOnSuccessListener {
//                Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show()
//
//            }.addOnFailureListener {
//                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
//            }
//        }

//       gotologin.setOnClickListener {
//            val glogin = Intent(this, login::class.java)
//            startActivity(glogin)
            createAccount(name, email, password)
      }

//       gotologin.setOnClickListener {
//            val glogin = Intent(this, login::class.java)
//            startActivity(glogin)
//
//
   }

    private fun createAccount(name: String, email: String, password: String) {


        val fAuth = FirebaseAuth.getInstance()
        val progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Creating")
        progressDialog.setMessage("Account")
        progressDialog.show()
        fAuth.createUserWithEmailAndPassword(email.trim { it <= ' ' }, password.trim { it <= ' ' })
            .addOnSuccessListener {
                val profileChangeRequest = UserProfileChangeRequest.Builder()
                    .setDisplayName(name).build()
                FirebaseAuth.getInstance().currentUser!!.updateProfile(profileChangeRequest)
                progressDialog.cancel()
                Toast.makeText(this, "Account Created", Toast.LENGTH_SHORT).show()
//                name.setText("")
//                email.setText("")
//                password.setText("")
            }
            .addOnFailureListener { e ->
                progressDialog.cancel()
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }


        val gotologin = findViewById<TextView>(R.id.goToLogin)
        gotologin.setOnClickListener {
            val glogin = Intent(this, login::class.java)
           startActivity(glogin)

    }}




    override fun onStart() {
        super.onStart()
        if (FirebaseAuth.getInstance().currentUser != null) {
            startActivity(Intent(this@MainActivity, Home::class.java))
        }
    }
}