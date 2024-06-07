package com.example.mealmanagement.food

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.session.session
import com.example.mealmanagement.ui.theme.*
import com.example.mealmanagement.viewmodel.FoodViewModel

data class FoodFormState(
    var name: TextFieldValue = TextFieldValue(""),
    var calories: TextFieldValue = TextFieldValue(""),
    var imageUrl: TextFieldValue = TextFieldValue(""),
    var ingredients: TextFieldValue = TextFieldValue(""),
    var instructions: TextFieldValue = TextFieldValue(""),
    var address: TextFieldValue = TextFieldValue("")
)

@Composable
fun AddFood(navController: NavController, foodViewModel: FoodViewModel) {
    var formState by remember { mutableStateOf(FoodFormState()) }
    var context = LocalContext.current
    var idUser = session.data?:"None"
    BaseScreen(navController) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            BannerItem(height = 67, img = R.drawable.banner_2, text = "Thêm Món Ăn", fontSize = 24, navController)
            LazyColumn(
                modifier = Modifier.padding(20.dp)
            ) {
                item {
                    DetailFoodToAdd("Tên*", formState.name) { formState = formState.copy(name = it) }
                }
                item {
                    DetailFoodToAdd("Tổng Calo", formState.calories) { formState = formState.copy(calories = it) }
                }
                item {
                    DetailFoodToAdd("Hình Ảnh", formState.imageUrl) { formState = formState.copy(imageUrl = it) }
                }
                item {
                    DetailFoodToAdd("Thành Phần*", formState.ingredients, isLong = true) { formState = formState.copy(ingredients = it) }
                }
                item {
                    DetailFoodToAdd("Cách làm", formState.instructions, isLong = true) { formState = formState.copy(instructions = it) }
                }
                item {
                    DetailFoodToAdd("Địa chỉ", formState.address, isLong = true) { formState = formState.copy(address = it) }
                }
                item {
                    ButtonAdd {
                        foodViewModel.saveFood(
                            FoodData(
                                idFood = "",
                                name= formState.name.text,
                                img = formState.imageUrl.text,
                                element = formState.ingredients.text,
                                totalCalo = formState.calories.text.toDouble(),
                                method =formState.instructions.text ,
                                address =formState.address.text,
                                idUser = idUser

                            ),
                            context = context)

                    }
                }
                item {
                    Spacer(modifier = Modifier.height(300.dp))
                }
            }
        }
    }
}

@Composable
fun DetailFoodToAdd(
    label: String,
    value: TextFieldValue,
    isLong: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
    ) {
        Text(
            text = "$label: ",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(if (isLong) 100.dp else 56.dp),
            singleLine = !isLong
        )
    }
}

@Composable
fun ButtonAdd(onClick: () -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenBackGround),
            onClick = onClick
        ) {
            Text(text = "Thêm", color = BlackText)
        }
    }
}
