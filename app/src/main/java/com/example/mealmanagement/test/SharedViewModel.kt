package com.example.mealmanagement.test



import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SharedViewModel : ViewModel() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("user")

    fun saveData(userData: UserData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(userData.userID).setValue(userData)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun retrieveData(userID: String, context: Context, data: (UserData) -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(userID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userData = snapshot.getValue(UserData::class.java)
                    if (userData != null) {
                        data(userData)
                    }
                } else {
                    Toast.makeText(context, "No User Data Found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
fun getAllData(context: Context, data: (ArrayList<UserData>) -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = ArrayList<UserData>()
                for (data in snapshot.children) {
                    val userData = data.getValue(UserData::class.java)
                    if (userData != null) {
                        list.add(userData)
                    }
                }
                data(list)
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun deleteData(userID: String, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(userID).removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully deleted data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}