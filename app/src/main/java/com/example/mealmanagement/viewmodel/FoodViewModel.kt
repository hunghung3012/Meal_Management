package com.example.mealmanagement.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.model.MenuData
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
class FoodViewModel : ViewModel() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("foods")

    fun saveFood(food: FoodData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val foodId = databaseReference.push().key // Tạo ID tự động
        if (foodId != null) {
            food.idFood = foodId
        }
        databaseReference.child(food.idFood).setValue(food)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun getFoodById(foodId: String): LiveData<FoodData> {
        val foodData = MutableLiveData<FoodData>()

        databaseReference.child(foodId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val food = snapshot.getValue(FoodData::class.java)
                    food?.let {
                        foodData.value = it
                    } ?: run {
                        // Handle trường hợp food là null nếu cần thiết
                        // Ví dụ: ghi log hoặc thông báo lỗi
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return foodData
    }
    fun getAllFoods(): LiveData<List<FoodData>> {
        val foodList = MutableLiveData<List<FoodData>>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull { it.getValue(FoodData::class.java) }
                foodList.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error here
            }
        })

        return foodList
    }

    fun getFoodByElement(element: String): LiveData<List<FoodData>> {
        val foodList = MutableLiveData<List<FoodData>>()

        databaseReference.orderByChild("element").equalTo(element)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children.mapNotNull { it.getValue(FoodData::class.java) }
                    foodList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return foodList
    }

    fun updateFood(food: FoodData) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(food.idFood).setValue(food)
            .addOnSuccessListener {
                // Handle success here
            }
            .addOnFailureListener { e ->
                // Handle error here
            }
    }
}