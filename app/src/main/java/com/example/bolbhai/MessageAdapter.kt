package com.example.bolbhai

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

class MessageAdapter(context: Context, resource: Int) : ArrayAdapter<ChatMessage>(context, resource) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val message = getItem(position)
        val inflater = LayoutInflater.from(context)

        val view: View = if (message?.senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            inflater.inflate(R.layout.item_message_sent, parent, false)
        } else {
            inflater.inflate(R.layout.item_message_received, parent, false)
        }

        val messageTextView: TextView = view.findViewById(R.id.messageTextView)
        val senderTextView: TextView = view.findViewById(R.id.senderTextView)

        messageTextView.text = message?.message

        // Display sender's name instead of sender's ID
        val senderName = if (message?.senderId == FirebaseAuth.getInstance().currentUser?.uid) {
            "You" // If the message is sent by the current user, display "You"
        } else {
            message?.senderName ?: "Unknown User" // Otherwise, display the sender's name or "Unknown User"
        }
        senderTextView.text = senderName

        return view
    }
}
