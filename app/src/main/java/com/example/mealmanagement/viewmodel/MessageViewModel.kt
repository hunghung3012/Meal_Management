package com.example.mealmanagement.viewmodel

import MessageData
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModel : ViewModel() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("messages")

    fun saveMessage(message: MessageData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val messageId = databaseReference.push().key // Tạo ID tự động
        if (messageId != null) {
            message.idMessage = messageId
        }
        databaseReference.child(message.idMessage).setValue(message)
            .addOnSuccessListener {
                Toast.makeText(context, "Gửi thành công", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun getMessageById(messageId: String): LiveData<MessageData> {
        val messageData = MutableLiveData<MessageData>()

        databaseReference.child(messageId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val message = snapshot.getValue(MessageData::class.java)
                    message?.let {
                        messageData.value = it
                    } ?: run {
                        // Handle trường hợp message là null nếu cần thiết
                        // Ví dụ: ghi log hoặc thông báo lỗi
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return messageData
    }

    fun getMessagesByUserIds(senderId: String, receiverId: String): LiveData<List<MessageData>> {
        val messageList = MutableLiveData<List<MessageData>>()
        val databaseReference = FirebaseDatabase.getInstance().reference.child("messages")

        val senderMessages = mutableListOf<MessageData>()
        val receiverMessages = mutableListOf<MessageData>()

        // First query: messages where senderId is the sender
        databaseReference.orderByChild("idSender").equalTo(senderId)
            .addValueEventListener (object : ValueEventListener {
                override fun onDataChange(senderSnapshot: DataSnapshot) {
                    for (senderMessageSnapshot in senderSnapshot.children) {
                        val senderMessage = senderMessageSnapshot.getValue(MessageData::class.java)
                        if (senderMessage != null && senderMessage.idReceiver == receiverId) {
                            senderMessages.add(senderMessage)
                        }
                    }
                    // Second query: messages where receiverId is the sender
                    databaseReference.orderByChild("idSender").equalTo(receiverId)
                        .addValueEventListener (object : ValueEventListener {
                            override fun onDataChange(receiverSnapshot: DataSnapshot) {
                                for (receiverMessageSnapshot in receiverSnapshot.children) {
                                    val receiverMessage = receiverMessageSnapshot.getValue(MessageData::class.java)
                                    if (receiverMessage != null && receiverMessage.idReceiver == senderId) {
                                        receiverMessages.add(receiverMessage)
                                    }
                                }
                                // Combine both lists and sort by time
                                val combinedList = (senderMessages + receiverMessages).sortedBy { it.time }
                                messageList.value = combinedList
                            }

                            override fun onCancelled(error: DatabaseError) {
                                // Handle error here
                            }
                        })
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return messageList
    }

    fun updateMessage(message: MessageData) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(message.idMessage).setValue(message)
            .addOnSuccessListener {
                // Handle success here
            }
            .addOnFailureListener { e ->
                // Handle error here
            }
    }

    fun deleteMessage(messageId: String, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(messageId).removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully deleted message", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}
