package com.example.mealmanagement.food

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.ui.theme.BlackText
import com.example.mealmanagement.ui.theme.GreenBackGround2
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.inter_bold
import com.example.mealmanagement.ui.theme.inter_light

data class MealItem(val name: String, val calories: String)
@Composable
fun DetailMeal() {
    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BannerItem(98,R.drawable.banner_2,"Thực đơn hôm nay",26)
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                item {
                    ListTest("Buổi Sáng", listOf(
                        MealItem("Cơm xào ốc hến", "102"),
                        MealItem("Bánh tráng trộn", "106"),
                        MealItem("Bít tết xào", "203")
                    ))
                }
                item {
                    ListTest("Buổi Trưa", listOf(
                        MealItem("Phở bò", "150"),
                        MealItem("Cơm tấm", "250"),
                        MealItem("Gà nướng", "300")
                    ))
                }
                item {
                    ListTest("Buổi Tối", listOf(
                        MealItem("Salad rau", "80"),
                        MealItem("Soup gà", "120"),
                        MealItem("Cá kho", "200")
                    ))
                }
                item {
                   Spacer(modifier = Modifier.height(100.dp) )
                }
            }

        }
    }
}
@Composable
fun ListTest(time: String, mealList: List<MealItem>) {
    Column {
        TimeMeal(time)
        Spacer(modifier = Modifier.height(10.dp))
        mealList.forEach { meal ->
            TimeMealItem(meal.name, meal.calories)
            Spacer(modifier = Modifier.height(10.dp))
        }
        ButtonPlus()
    }
}
@Composable
fun TimeMeal(time:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .background(Color.Transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .border(BorderStroke(2.dp, Color(151, 151, 151)), shape = RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text(
                text = time,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                color = GreenText,
                modifier = Modifier.background(Color.Transparent)

            )
        }
        TextCalo("345")

    }

}
@Composable
fun TimeMealItem(nameOfFood:String,calo:String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.2.dp, Color.Black), shape = RoundedCornerShape(12.dp)),
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(GreenBackGround2),
            shape = RoundedCornerShape(14.dp),
            contentPadding = PaddingValues(10.dp, 16.dp),
            onClick = { /*TODO*/ }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Image(
                    painter = painterResource(id = R.drawable.food1)
                    , contentDescription = "",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color.Transparent),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(14.dp))
                Column(
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = nameOfFood,
                        color = BlackText,
                        fontSize = 20.sp,
                        fontFamily = inter_bold,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.width(250.dp)
                    )
                    Text(
                        text = "1 cái",
                        color = BlackText,
                        fontSize = 16.sp,
//                        fontFamily = ,
                        fontWeight = FontWeight(400),
                        modifier = Modifier.width(250.dp)
                    )

                }

//                Spacer(modifier = Modifier.width(150.dp))
                TextCalo(calo)


            }
        }

    }
//    Spacer(modifier = Modifier.height(8.dp))

}
@Composable
fun TextCalo(text:String) {
    Text(
        text = text+" kcal",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = inter_bold,
        color = BlackText,
    )
}