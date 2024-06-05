package com.example.mealmanagement.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.mealmanagement.model.FoodData
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FoodViewModel : ViewModel() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("user")

    fun saveFood(foodData: FoodData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(foodData.idFood).setValue(foodData)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun getFood(userID: String, context: Context, data: (FoodData) -> Unit) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(userID).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val foodData = snapshot.getValue(FoodData::class.java)
                    if (foodData != null) {
                        data(foodData)
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

    fun deleteFood(userID: String, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(userID).removeValue()
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully deleted data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
}