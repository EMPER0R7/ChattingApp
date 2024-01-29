package com.example.bolbhai

data class ChatMessage(
    val senderId: String = "",
    val senderName: String = "",
    val message: String = "",
    val timestamp: Long = 0
)
