package com.example.mealmanagement

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealmanagement.food.AddFood

import com.example.mealmanagement.food.DetailFood
import com.example.mealmanagement.food.DetailMeal
import com.example.mealmanagement.food.DetailMyFood
import com.example.mealmanagement.food.EditMyFood
import com.example.mealmanagement.food.FindFood
import com.example.mealmanagement.food.ListDay
import com.example.mealmanagement.food.ListMeal
import com.example.mealmanagement.food.MyFood
import com.example.mealmanagement.home.Home
import com.example.mealmanagement.message.MessagingScreen
import com.example.mealmanagement.model.DetailMealData
import com.example.mealmanagement.route.Screen
import com.example.mealmanagement.session.session
import com.example.mealmanagement.sign.EditInforUser
import com.example.mealmanagement.sign.SignIn
import com.example.mealmanagement.sign.SignUp
import com.example.mealmanagement.sign.UpdateInfo
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.FoodViewModel
import com.example.mealmanagement.viewmodel.MealViewModel
import com.example.mealmanagement.viewmodel.MenuViewModel
import com.example.mealmanagement.viewmodel.MessageViewModel
import com.example.mealmanagement.viewmodel.UserViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()
    val menuViewModel: MenuViewModel = viewModel()
    val foodViewModel: FoodViewModel = viewModel()
    val mealViewModel: MealViewModel = viewModel()
    val detailMealViewModel: DetailMealViewModel = viewModel()
    val userViewModel: UserViewModel = viewModel()
    val messageViewModel: MessageViewModel = viewModel()
    NavHost(navController = navController,
        startDestination = "signIn"
//        startDestination = "message"
    ) {
        composable("home") {
            Home(navController,userViewModel,foodViewModel,mealViewModel)
        }
        composable("updateInfo") {
            UpdateInfo(navController,userViewModel)
        }
        composable("listMeal") { ListMeal(navController, menuViewModel, session.data) }
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

        composable("findFood/{mealId}") { backStackEntry ->
            val mealId = backStackEntry.arguments?.getString("mealId")
            FindFood(navController,mealId.toString(),mealViewModel,detailMealViewModel,foodViewModel)
        }
        //composable for detailFood
        composable("detailFood/{foodId}/{mealID}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            val mealID = backStackEntry.arguments?.getString("mealID")
            DetailFood(navController = navController, foodId = foodId.toString(),mealID.toString(), foodViewModel = foodViewModel,detailMealViewModel = detailMealViewModel)
        }

        composable("detailMyFood/{foodId}/{mealID}/{detailId}/{quantity}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            val mealID = backStackEntry.arguments?.getString("mealID")
            val detailId = backStackEntry.arguments?.getString("detailId")
            val quantity = backStackEntry.arguments?.getString("quantity")
            var item = DetailMealData(detailId.toString(),mealID.toString(),foodId.toString(),quantity.toString().toInt())
            DetailMyFood(navController = navController, item, foodViewModel = foodViewModel,detailMealViewModel = detailMealViewModel)
        }

        composable("addFood") { backStackEntry ->


            AddFood(navController = navController,foodViewModel)
        }
        composable("myListFood") { backStackEntry ->
            MyFood(navController = navController,foodViewModel)
        }
            composable("editMyFood/{foodId}") { backStackEntry ->
            val foodId = backStackEntry.arguments?.getString("foodId")
            EditMyFood(navController = navController, foodViewModel = foodViewModel, foodId =foodId.toString() )
        }


        composable("signIn") {
            SignIn(navController,userViewModel)
        }
        composable("signInData/{user}/{pass}") { backStackEntry ->
            val user = backStackEntry.arguments?.getString("user")
            val pass = backStackEntry.arguments?.getString("pass")
            SignIn(navController,userViewModel,userViewModel.decodeEmail(user.toString()),pass.toString())
        }
        composable("signUp") {
            SignUp(navController,userViewModel)
        }

        composable("editInfo/{userID}") { backStackEntry ->
            val userID = backStackEntry.arguments?.getString("userID")
            EditInforUser(navController,userViewModel,userID.toString())
        }
        composable("message") {
            MessagingScreen(navController,messageViewModel)
        }


    }

}