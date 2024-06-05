package com.example.mealmanagement.route

sealed class Screen(val route: String) {
    object ListDay : Screen("list_day")
    object AddFood : Screen("add_food")
    object DetailFood : Screen("detail_food")
    object DetailMeal : Screen("detail_meal")
    object FindFood : Screen("find_food")
    object ListMeal : Screen("list_meal")
    object MyFood : Screen("my_food")
    object BaseScreen : Screen("base_screen")
    object Home : Screen("home")
    object ImagePickerApp : Screen("image_picker_app")
    object Menu : Screen("menu")
    object AddDataScreen : Screen("add_data_screen")
    object GetDataScreen : Screen("get_data_screen")
}