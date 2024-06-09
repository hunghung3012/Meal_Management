package com.example.mealmanagement

import MessageData
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
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

import com.example.mealmanagement.model.DetailMealData
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.model.MealData
import com.example.mealmanagement.model.MenuData
import com.example.mealmanagement.session.session

import com.example.mealmanagement.sign.SignIn
import com.example.mealmanagement.sign.SignUp
import com.example.mealmanagement.sign.UpdateInfo


import com.example.mealmanagement.ui.theme.MealManagementTheme
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.FoodViewModel
import com.example.mealmanagement.viewmodel.MealViewModel
import com.example.mealmanagement.viewmodel.MenuViewModel
import com.example.mealmanagement.viewmodel.MessageViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class MainActivity : ComponentActivity() {

    private val menuViewModel: MenuViewModel by viewModels()
    private val foodViewModel: FoodViewModel by viewModels()
    private val detailMealViewModel: DetailMealViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    private val messageViewModel: MessageViewModel by viewModels()

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
//                    addTestMessages()
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTestMessages() {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
        val message1 = MessageData("", "test1@gmail.com", "123", "Hello User2!", LocalDateTime.parse("2023-01-01 10:00:00", formatter).format(DateTimeFormatter.ISO_DATE_TIME), isSender = true)
        val message2 = MessageData("", "123", "test1@gmail.com", "Hi User1!", LocalDateTime.parse("2023-01-01 10:05:00", formatter).format(DateTimeFormatter.ISO_DATE_TIME), isSender = false)
        val message3 = MessageData("", "test1@gmail.com", "123", "How are you?", LocalDateTime.parse("2023-01-01 10:10:00", formatter).format(DateTimeFormatter.ISO_DATE_TIME), isSender = true)
        val message4 = MessageData("", "123", "test1@gmail.com", "I'm good, thanks!", LocalDateTime.parse("2023-01-01 10:15:00", formatter).format(DateTimeFormatter.ISO_DATE_TIME), isSender = false)

        messageViewModel.saveMessage(message1, this)
        messageViewModel.saveMessage(message2, this)
        messageViewModel.saveMessage(message3, this)
        messageViewModel.saveMessage(message4, this)
    }
//    fun addTestFood() {
//        val food1 = FoodData("", "Bánh mì", "Bánh mì, thịt, rau sống", 250.0, "Nướng", "123 Đường ABC")
//        val food2 = FoodData("", "Phở", "Phở bò", 350.0, "Luộc", "456 Đường XYZ")
//        val food3 = FoodData("", "Gỏi cuốn", "Tôm, thịt, rau sống", 150.0, "Cuốn", "789 Đường LMN")
//        val food4 = FoodData("", "Cơm rang", "Cơm, thịt, rau cải", 400.0, "Xào", "101 Đường PQR")
//        val food5 = FoodData("", "Bún chả", "Bún, thịt heo nướng", 300.0, "Nướng", "202 Đường STU")
//
//        foodViewModel.saveFood(food1, this)
//        foodViewModel.saveFood(food2, this)
//        foodViewModel.saveFood(food3, this)
//        foodViewModel.saveFood(food4, this)
//        foodViewModel.saveFood(food5, this)
//    }
    fun addTestMeals() {

        val meal1 = MealData("", "-NzXC5foKQ0HiwI2Rhcz", "Breakfast", 0)
        val meal2 = MealData("", "-NzXC5foKQ0HiwI2Rhcz", "Lunch", 0)
        val meal3 = MealData("", "-NzXC5foKQ0HiwI2Rhcz", "Dinner", 1)
        val meal4 = MealData("", "-NzXC5foKQ0HiwI2Rhcz", "Breakfast", 1)
        val meal5 = MealData("", "-NzXC5foKQ0HiwI2Rhcz", "Lunch", 2)

        mealViewModel.saveMeal(meal1, this)
        mealViewModel.saveMeal(meal2, this)
        mealViewModel.saveMeal(meal3, this)
        mealViewModel.saveMeal(meal4, this)
        mealViewModel.saveMeal(meal5, this)
    }
    fun addTestDetailMeals() {

        val meal1 = DetailMealData("", "-NzcTMGJOrUe862SdtZl", "-Nzbnul2ttC9I1D1luXV", 2)
        val meal2 = DetailMealData("", "-NzcTMGJOrUe862SdtZl", "-Nzbnul2ttC9I1D1luXV", 1)
        val meal3 = DetailMealData("", "-NzcTMGJOrUe862SdtZl", "--NzbnulG8oQpqqQNFERN", 3)
        val meal4 = DetailMealData("", "-NzcTMGQHC1SD_TAetS3", "-NzbnulJyI_i1zzDB4av", 1)
        val meal5 = DetailMealData("", "-NzcTMGQHC1SD_TAetS3", "-Nzbnul4Ojq5eqyonilZ", 2)

        detailMealViewModel.saveDetailMeal(meal1, this)
        detailMealViewModel.saveDetailMeal(meal2, this)
        detailMealViewModel.saveDetailMeal(meal3, this)
        detailMealViewModel.saveDetailMeal(meal4, this)
        detailMealViewModel.saveDetailMeal(meal5, this)
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
@Composable
fun ImageFromUrl(imageUrl: String,modifier: Modifier) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = imageUrl).apply(block = fun ImageRequest.Builder.() {
            crossfade(true)
        }).build()
    )
    androidx.compose.foundation.Image(
        painter = painter,
        contentDescription = null,
        modifier = modifier.fillMaxSize()
    )
}
