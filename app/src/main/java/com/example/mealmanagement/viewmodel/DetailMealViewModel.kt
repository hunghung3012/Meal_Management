package com.example.mealmanagement.viewmodel

import androidx.lifecycle.ViewModel
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mealmanagement.model.DetailMealData
import com.example.mealmanagement.model.MenuData
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMealViewModel: ViewModel()  {
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("detailMeal")

    fun saveDetailMeal(detailMeal: DetailMealData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val detailMealId = databaseReference.push().key // Tạo ID tự động
        if (detailMealId != null) {
            detailMeal.idDetailMeal = detailMealId
        }
        databaseReference.child(detailMeal.idDetailMeal).setValue(detailMeal)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }

    fun getDetailMealById(detailMealId: String): LiveData<DetailMealData> {
        val detailMealData = MutableLiveData<DetailMealData>()

        databaseReference.child(detailMealId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val detailMeal = snapshot.getValue(DetailMealData::class.java)
                    detailMeal?.let {
                        detailMealData.value = it
                    } ?: run {
                        // Handle trường hợp detailMeal là null nếu cần thiết
                        // Ví dụ: ghi log hoặc thông báo lỗi
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return detailMealData
    }

    fun getDetailMealsByMealId(mealId: String): LiveData<List<DetailMealData>> {
        val detailMealList = MutableLiveData<List<DetailMealData>>()

        databaseReference.orderByChild("idMeal").equalTo(mealId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children.mapNotNull { it.getValue(DetailMealData::class.java) }
                    detailMealList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return detailMealList
    }

    fun updateDetailMeal(detailMeal: DetailMealData) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(detailMeal.idDetailMeal).setValue(detailMeal)
            .addOnSuccessListener {
                // Handle success here
            }
            .addOnFailureListener { e ->
                // Handle error here
            }
    }
    fun deleteDetailMealsByMealId(mealId: String, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.orderByChild("idMeal").equalTo(mealId)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (childSnapshot in snapshot.children) {
                        childSnapshot.ref.removeValue()
                            .addOnSuccessListener {
                                Toast.makeText(context, "Successfully deleted detail meal data", Toast.LENGTH_SHORT).show()
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                            }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })
    }
}