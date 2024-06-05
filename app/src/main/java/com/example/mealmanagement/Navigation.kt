package com.example.mealmanagement

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealmanagement.food.DetailMeal
import com.example.mealmanagement.food.ListDay
import com.example.mealmanagement.food.ListMeal
import com.example.mealmanagement.home.Home
import com.example.mealmanagement.route.Screen
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.FoodViewModel
import com.example.mealmanagement.viewmodel.MealViewModel
import com.example.mealmanagement.viewmodel.MenuViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val menuViewModel: MenuViewModel = viewModel()
    val foodViewModel: FoodViewModel = viewModel()
    val mealViewModel: MealViewModel = viewModel()
    val detailMealViewModel: DetailMealViewModel = viewModel()
    NavHost(navController = navController, startDestination = "listMeal") {
        composable(Screen.Home.route) {
            Home()
        }
        composable("listMeal") { ListMeal(navController, menuViewModel, "User1") }
        composable("listDay/{menuId}") { backStackEntry ->
            val menuId = backStackEntry.arguments?.getString("menuId")
            ListDay(navController, menuViewModel,menuId.toString())
        }

        composable("detailMeal/{menuId}/{day}") { backStackEntry ->
            val menuId = backStackEntry.arguments?.getString("menuId")
            val day = backStackEntry.arguments?.getString("day")
            DetailMeal(navController, menuId.toString(),day.toString(), mealViewModel,detailMealViewModel,foodViewModel)
//        DetailMeal(navController: NavController,idMenu:String,day:String,mealModel:MealViewModel,detailMealViewModel: DetailMealViewModel,foodViewModel: FoodViewModel) {
        }
    }
}