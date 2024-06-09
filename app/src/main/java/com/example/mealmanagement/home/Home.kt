package com.example.mealmanagement.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.model.UserData
import com.example.mealmanagement.session.session
import com.example.mealmanagement.ui.theme.inter_medium
import com.example.mealmanagement.viewmodel.FoodViewModel
import com.example.mealmanagement.viewmodel.MealViewModel
import com.example.mealmanagement.viewmodel.UserViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
fun getDay():String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("d 'thg' M", Locale("vi"))
    return currentDate.format(formatter)
}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Home(navController: NavController,userViewModel: UserViewModel,foodViewModel: FoodViewModel,mealViewModel: MealViewModel){
    val user = userViewModel.getUserByUsername(session.data).observeAsState().value ?: UserData()
    val foodList by foodViewModel.getFoodByIdUser(session.data).observeAsState(emptyList())
    val mealList by foodViewModel.getFoodByIdUser(session.data).observeAsState(emptyList())

    BaseScreen(navController) {
        Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .background(Color(195, 225, 213))
                .padding(20.dp, 20.dp),

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Hôm nay",
                    color = Color(87, 83, 80),
                    fontSize = 22.sp,
                    fontFamily = inter_medium,
                    fontWeight = FontWeight.Bold

                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                )
                {
                    Icon(
                        painter = painterResource(
                            id = R.drawable.baseline_calendar_today_24
                        ), contentDescription = getDay()
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = getDay(),
                        color = Color(87, 83, 80),
                        fontSize = 22.sp,
                        fontFamily = inter_medium,
                        fontWeight = FontWeight.Bold
                    )
                }


            }
            Spacer(modifier = Modifier.height(50.dp))
            InforCalo(foodList.size, mealList.size, calculateCalo(user.weight,user.height).toString())

        }
            LazyColumn {
                item{
                    InforHealthy(user)
                }
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor = Color(54, 155, 113))
                            ,onClick = {
                            navController.navigate("updateInfo")
                        }) {
                            Text(text = "Cập nhật")
                        }
                    }
                }

            }


        }

    }
}
@Composable
fun InforCalo(food:Int, meal:Int,calo:String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InforCalo_Item(food.toString())
        Column(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(54, 155, 113)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = "Calo cần thiết \n$calo",
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                color = Color(255, 226, 249),
                fontFamily = inter_medium
            )

        }
        InforCalo_Item(meal.toString())
    }
}
@Composable
fun InforCalo_Item(string:String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = string,
            textAlign = TextAlign.Center ,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(54, 155, 113),
        )
        Text(text = "Bữa Ăn",
            textAlign = TextAlign.Center ,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(54, 155, 113),
        )
    }
}
@Composable
fun InforHealthy(user:UserData) {
    Row(
        modifier = Modifier.padding(30.dp,20.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(251, 236, 208))
                .height(250.dp)
                .weight(1.7f)
                .padding(10.dp),

        ) {
            Text( text = "Thông Tin",
                color = Color(226, 128, 74),
                fontSize = 24.sp,
                fontFamily = inter_medium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                )
            InforHealthyItem(user)
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .height(200.dp)

        ) {
            Image(
                modifier = Modifier
                    .size(71.dp, 200.dp)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.banner_1), contentDescription = ""
            )
        }
    }

}
@Composable
fun InforHealthyItem(user:UserData){
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        InforHealthyItemButton("Cân Nặng", user.weight.toString(),"kg")
        InforHealthyItemButton("Chiều Cao", user.height.toString(),"cm")
        InforHealthyItemNoButton("BMI",calculateBMI(user.weight,user.height).toString())
        InforHealthyItemNoButton("Thể Trạng",checkHealthy(calculateBMI(user.weight,user.height)))
    }
}
@Composable
fun InforHealthyItemButton(name:String , value:String, donvi:String) {

        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.height(50.dp)
        ) {
            Text(text = "$name:$value$donvi",fontSize = 18.sp)
        }


}
@Composable
fun InforHealthyItemNoButton(health:String,bmi:String) {

    Row(
        modifier = Modifier.height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text ="$health: ",
            fontSize = 18.sp)
        Text(text = "$bmi",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

    }


}
fun calculateBMI(weight: Double, height: Double): Double {
    if (height == 0.0 || weight == 0.0)
        return 0.0
    else {
        // làm tròn bmi đến 2 chữ số thập phân
        val bmi = weight / (height * 0.01 * height * 0.01)
        return bmi.roundTo(2)
    }
}

fun checkHealthy(bmi: Double): String {
    return when {
        bmi < 18.5 -> "Thiếu cân"
        bmi in 18.5..24.9 -> "Bình thường"
        bmi in 25.0..29.9 -> "Thừa cân"
        else -> "Béo phì"
    }
}

fun Double.roundTo(n: Int): Double {
    return "%.${n}f".format(this).toDouble()
}
fun calculateCalo(weight: Double, height: Double):Double {
    return 6.25* height + 10 * weight - 5 * 20 + 5
}