package com.example.mealmanagement.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.ui.theme.GreenBackGround
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.WhiteBackGround
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.FoodViewModel
import com.example.mealmanagement.viewmodel.MealViewModel

@Composable
fun FindFood(navController: NavController, mealId: String, mealViewModel: MealViewModel, detailMealViewModel: DetailMealViewModel, foodViewModel: FoodViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val foodList by foodViewModel.getAllFoods().observeAsState(listOf())
    val filteredFoodList = foodList.filter {
        it.name.contains(searchQuery, ignoreCase = true)
    }

    BaseScreen(navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Text(text = mealId)
            BannerItem(height = 98, img = R.drawable.banner_2, text = "Lựa chọn món ăn", fontSize = 22, navController)
            SingleButton(GreenText, GreenBackGround, R.drawable.baseline_emoji_people_24){

            }
            SerchBar(onSearchQueryChange = { searchQuery = it })

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                filteredFoodList.forEach { food ->
                    item {
                        TimeMealItem(food.name, food.totalCalo.toString(),1) {
                            navController.navigate("detailFood/${food.idFood}/${mealId}")
//                            DetailFood(navController = navController,food)
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Composable
fun SerchBar(onSearchQueryChange: (String) -> Unit) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    OutlinedTextField(
        value = searchQuery,
        onValueChange = {
            searchQuery = it
            onSearchQueryChange(it.text)
        },
        placeholder = {
            Text(text = "Tìm kiếm", fontSize = 16.sp, color = Color.Gray)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = null,
                tint = Color.Gray
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(WhiteBackGround, RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        singleLine = true
    )
}
