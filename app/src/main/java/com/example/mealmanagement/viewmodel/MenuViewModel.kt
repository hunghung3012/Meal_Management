package com.example.mealmanagement.viewmodel


import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealmanagement.model.MenuData
import com.google.firebase.database.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MenuViewModel : ViewModel() {

    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference.child("thucdon")

    fun saveMenu(menu: MenuData, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val menuId = databaseReference.push().key // Tạo ID tự động
        if (menuId != null) {
            menu.IDThucDon = menuId
        }
        databaseReference.child(menu.IDThucDon).setValue(menu)
            .addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
    }
    fun getMenuById(menuId: String): LiveData<MenuData> {
        val menuData = MutableLiveData<MenuData>()

        databaseReference.child(menuId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val menu = snapshot.getValue(MenuData::class.java)
                    menu?.let {
                        menuData.value = it
                    } ?: run {
                        // Handle trường hợp menu là null nếu cần thiết
                        // Ví dụ: ghi log hoặc thông báo lỗi
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return menuData
    }

    fun getThucDonByUserId(userId: String): LiveData<List<MenuData>> {
        val thucDonList = MutableLiveData<List<MenuData>>()

        databaseReference.orderByChild("iduser").equalTo(userId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val list = snapshot.children.mapNotNull { it.getValue(MenuData::class.java) }
                    thucDonList.value = list
                }

                override fun onCancelled(error: DatabaseError) {
                    // Handle error here
                }
            })

        return thucDonList
    }
    fun updateMenu(menu: MenuData) = CoroutineScope(Dispatchers.IO).launch {
        databaseReference.child(menu.IDThucDon).setValue(menu)
            .addOnSuccessListener {
                // Handle success here
            }
            .addOnFailureListener { e ->
                // Handle error here
            }
    }
}