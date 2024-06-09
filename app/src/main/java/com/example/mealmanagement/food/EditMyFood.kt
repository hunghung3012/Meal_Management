package com.example.mealmanagement.food

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.session.session
import com.example.mealmanagement.viewmodel.FoodViewModel

@Composable
fun EditMyFood(navController: NavController, foodViewModel: FoodViewModel, foodId: String) {
    val food by foodViewModel.getFoodById(foodId).observeAsState()

    var formState by remember { mutableStateOf<FoodFormState?>(null) }

    // Sử dụng LaunchedEffect để khởi tạo formState khi dữ liệu food thay đổi
    LaunchedEffect(food) {
        if (food != null) {
            formState = FoodFormState(
                name = TextFieldValue(food!!.name),
                calories = TextFieldValue(food!!.totalCalo.toString()),
                imageUrl = TextFieldValue(food!!.img),
                ingredients = TextFieldValue(food!!.element),
                instructions = TextFieldValue(food!!.method),
                address = TextFieldValue(food!!.address)
            )
        }
    }

    val context = LocalContext.current
    val idUser = session.data ?: "None"

    if (formState == null) {
        // Hiển thị giao diện đang tải trong khi chờ dữ liệu
        CircularProgressIndicator()
    } else {
        BaseScreen(navController) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                BannerItem(
                    height = 67,
                    img = R.drawable.banner_2,
                    text = "Cập nhật món ăn",
                    fontSize = 24,
                    navController = navController
                )
                LazyColumn(
                    modifier = Modifier.padding(20.dp)
                ) {
                    item {
                        DetailFoodToAdd("Tên*", formState!!.name) {
                            formState = formState!!.copy(name = it)
                        }
                    }
                    item {
                        DetailFoodToAdd("Tổng Calo", formState!!.calories, keyboardType = KeyboardType.Number) {
                            formState = formState!!.copy(calories = it)
                        }

                    }
                    item {
                        DetailFoodToAdd("Hình Ảnh", formState!!.imageUrl) {
                            formState = formState!!.copy(imageUrl = it)
                        }
                    }
                    item {
                        DetailFoodToAdd(
                            "Thành Phần*",
                            formState!!.ingredients,
                            isLong = true
                        ) { formState = formState!!.copy(ingredients = it) }
                    }
                    item {
                        DetailFoodToAdd(
                            "Cách làm",
                            formState!!.instructions,
                            isLong = true
                        ) { formState = formState!!.copy(instructions = it) }
                    }
                    item {
                        DetailFoodToAdd("Địa chỉ", formState!!.address, isLong = true) {
                            formState = formState!!.copy(address = it)
                        }
                    }
                    item {
                        ButtonAdd("Cập nhật") {
                            foodViewModel.updateFood(
                                FoodData(
                                    idFood = foodId,
                                    name = formState!!.name.text,
                                    img = formState!!.imageUrl.text,
                                    element = formState!!.ingredients.text,
                                    totalCalo = formState!!.calories.text.toDouble(),
                                    method = formState!!.instructions.text,
                                    address = formState!!.address.text,
                                    idUser = idUser
                                ),
                                context = context
                            )
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(300.dp))
                    }
                }
            }
        }
    }
}


