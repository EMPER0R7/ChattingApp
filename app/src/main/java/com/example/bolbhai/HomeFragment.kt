package com.example.bolbhai

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment() {


    private lateinit var btnchat:Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_home, container, false)
        btnchat=view.findViewById(R.id.openchat)

        btnchat.setOnClickListener {
            val chatintent=Intent(context,GroupChatActivity::class.java)
            startActivity(chatintent)
        }
        return view
    }


}