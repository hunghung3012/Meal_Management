package com.example.mealmanagement

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mealmanagement.food.AddFood
import com.example.mealmanagement.food.DetailFood
import com.example.mealmanagement.food.DetailMeal
import com.example.mealmanagement.food.FindFood
import com.example.mealmanagement.food.ListDay
import com.example.mealmanagement.food.ListMeal
import com.example.mealmanagement.food.MyFood
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.home.Home
import com.example.mealmanagement.home.ImagePickerApp


import com.example.mealmanagement.menu.Menu
import com.example.mealmanagement.model.MenuData
import com.example.mealmanagement.test.AddDataScreen
import com.example.mealmanagement.test.GetDataScreen
import com.example.mealmanagement.test.SharedViewModel
import com.example.mealmanagement.ui.theme.MealManagementTheme
import com.example.mealmanagement.viewmodel.MenuViewModel


class MainActivity : ComponentActivity() {
    private val sharedViewModel: SharedViewModel by viewModels()
    private val menuViewModel: MenuViewModel by viewModels()


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MealManagementTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                }
            }
        }
    }
    fun addTestMenu() {


        val thucDon1 = MenuData("", "User1", "ThucDon1", "2023-01-01")
        val thucDon2 = MenuData("", "User2", "ThucDon2", "2023-01-02")
        val thucDon3 = MenuData("", "User3", "ThucDon3", "2023-01-03")

        menuViewModel.saveMenu(thucDon1, this)
        menuViewModel.saveMenu(thucDon2, this)
        menuViewModel.saveMenu(thucDon3, this)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MealManagementTheme {
        Greeting("Android")
    }
}