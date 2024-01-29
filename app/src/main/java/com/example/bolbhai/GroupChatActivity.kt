package com.example.bolbhai

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class GroupChatActivity : AppCompatActivity() {

    private lateinit var messageEditText: EditText
    private lateinit var sendButton: Button
    private lateinit var messageListView: ListView

    private lateinit var databaseReference: DatabaseReference
    private lateinit var messageAdapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_group_chat)

        messageEditText = findViewById(R.id.messageEditText)
        sendButton = findViewById(R.id.sendButton)
        messageListView = findViewById(R.id.messageListView)

        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: "unknown"
        // Assuming you have a FirebaseUser object after successful sign-in
        val firebaseUser = FirebaseAuth.getInstance().currentUser

// Get the sender's name from the FirebaseUser object
        val senderName = firebaseUser?.displayName ?: "Unknown User"


        // Set up Firebase Realtime Database
        databaseReference = FirebaseDatabase.getInstance().reference.child("groupChat")

        // Set up message adapter
        messageAdapter = MessageAdapter(this, R.layout.item_message_sent)
        messageListView.adapter = messageAdapter

        // Load existing messages
        loadMessages()

        sendButton.setOnClickListener {
            val messageText = messageEditText.text.toString().trim()
            if (messageText.isNotEmpty()) {
                val message = ChatMessage(senderId = userId,senderName=senderName, message = messageText, timestamp = System.currentTimeMillis())
                sendMessage(message)
                messageEditText.text.clear()
            }
        }
    }

    private fun loadMessages() {
        val messageQuery = databaseReference.limitToLast(50)

        messageQuery.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                val message = snapshot.getValue(ChatMessage::class.java)
                if (message != null) {
                    messageAdapter.add(message)
                }
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle updated messages if needed
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                // Handle deleted messages if needed
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                // Handle moved messages if needed
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle database errors if needed
            }
        })
    }

    private fun sendMessage(message: ChatMessage) {
        databaseReference.push().setValue(message)
    }
}
