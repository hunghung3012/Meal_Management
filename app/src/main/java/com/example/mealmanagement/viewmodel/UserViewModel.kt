package com.example.mealmanagement.viewmodel


import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealmanagement.model.UserData
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("user")
    fun encodeEmail(email: String): String {
        return email.replace(".", ",")
    }

    fun decodeEmail(email: String): String {
        return email.replace(",", ".")
    }
    @SuppressLint("SuspiciousIndentation")
    fun saveUser(user: UserData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val encodedUser = encodeEmail(user.user)
        databaseReference.child(encodedUser).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved user data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
    fun getUserByUsername(username: String): LiveData<UserData> {
        val userData = MutableLiveData<UserData>()
        val encodedUser = encodeEmail(username)

        databaseReference.child(encodedUser)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(UserData::class.java)
                    user?.let {
                        // Giải mã email trước khi gán vào LiveData nếu cần
                        it.user = decodeEmail(it.user)
                        userData.value = it
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return userData
    }

    fun updateUser(user: UserData,context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val encodedUser = encodeEmail(user.user)
        databaseReference.child(encodedUser).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully update user data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                // Handle error here
            }
    }
    fun getAllUsers(): LiveData<List<UserData>> {
        val usersList = MutableLiveData<List<UserData>>()

        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userList = snapshot.children.mapNotNull { it.getValue(UserData::class.java) }
                usersList.value = userList
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error here
            }
        })

        return usersList
    }
}
