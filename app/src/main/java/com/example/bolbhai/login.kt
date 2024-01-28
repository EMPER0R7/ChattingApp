package com.example.bolbhai

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class login : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    companion object{
        const val KEY="login.KEY.email"
        const val KEY2="login.KEY.name"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val nameid=findViewById<TextInputEditText>(R.id.nameid)
        val password=findViewById<TextInputEditText>(R.id.password)
        val lgnbtn=findViewById<Button>(R.id.button2)
        lgnbtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                val email: String = nameid.text.toString()
                val password: String = password.text.toString()
                logini(email, password)
            }

            private fun logini(email: String, password: String) {
                val progressDialog = ProgressDialog(this@login)
                progressDialog.setTitle("Login ")
                progressDialog.setMessage("In process")
                progressDialog.show()
                val firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signInWithEmailAndPassword(
                    email.trim { it <= ' ' },
                    password.trim { it <= ' ' })
                    .addOnSuccessListener { authResult ->
                        progressDialog.cancel()
                        Toast.makeText(
                            this@login,
                            "Login Successful",
                            Toast.LENGTH_SHORT
                        ).show()
                        startActivity(Intent(this@login, Home::class.java))
                    }



                    .addOnFailureListener { e ->
                        progressDialog.cancel()
                        Toast.makeText(this@login, e.message, Toast.LENGTH_SHORT).show()
                    }
            }
        })
    }

//            if(name.isNotEmpty()){
//                readData(name)
//            }else{
//                Toast.makeText(this,"Enter login id", Toast.LENGTH_SHORT).show()
//            }
//        }



//    private fun readData(name: String) {
//        databaseReference=FirebaseDatabase.getInstance().getReference("Users")
//        databaseReference.child(name).get().addOnSuccessListener {
//            if(it.exists()){
//                val email=it.child("email").value
//                val name=it.child("name").value
//                val intenthome=Intent(this,Home::class.java)
//
//                intenthome.putExtra(KEY,email.toString())
//                intenthome.putExtra(KEY2,name.toString())
//                startActivity(intenthome)
//
//            }else{
//                Toast.makeText(this,"User not exists", Toast.LENGTH_SHORT).show()
//            }
//
//        }.addOnFailureListener{
//            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
//        }
//
//    }



    
}


