package com.example.fcchatapp.chatlist

data class ChatRoomItem(
    val chatRoomId: String,
    val lastMessage: String,
    val otherUserName: String,
)