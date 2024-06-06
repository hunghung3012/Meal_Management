package com.example.mealmanagement.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mealmanagement.model.MealData
import com.example.mealmanagement.model.MenuData
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MealViewModel: ViewModel() {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("meal")


    @SuppressLint("SuspiciousIndentation")
    fun saveMeal(meal: MealData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val mealId = databaseReference.push().key // Tạo ID tự động
            if (mealId != null) {
            meal.idMeal = mealId
        }
        databaseReference.child(meal.idMeal).setValue(meal)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun getMealById(mealId: String): LiveData<MealData> {
        val mealData = MutableLiveData<MealData>()

        databaseReference.child(mealId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val meal = snapshot.getValue(MealData::class.java)
                    meal?.let {
                        mealData.value = it
                    } ?: run {
                        // Handle trường hợp meal là null nếu cần thiết
                        // Ví dụ: ghi log hoặc thông báo lỗi
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return mealData
    }

    fun getMealsByMenuId(idMenu: String): LiveData<List<MealData>> {
        val mealList = MutableLiveData<List<MealData>>()

        databaseReference.orderByChild("idMenu").equalTo(idMenu)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children.mapNotNull { it.getValue(MealData::class.java) }
                    mealList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return mealList
    }

    fun getMealsByMenuIdAndDay(idMenu: String,day:Int): LiveData<List<MealData>> {
        val mealList = MutableLiveData<List<MealData>>()

        databaseReference.orderByChild("idMenu").equalTo(idMenu)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children.mapNotNull { it.getValue(MealData::class.java) }
                    mealList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })



        return mealList
    }

    fun updateMeal(meal: MealData) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(meal.idMeal).setValue(meal)
            .addOnSuccessListener {
                // Handle success here
            }
            .addOnFailureListener { e ->
                // Handle error here
            }
    }

}