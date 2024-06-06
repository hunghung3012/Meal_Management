package com.example.mealmanagement.food

import android.content.Context
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.model.DetailMealData
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.model.MealData
import com.example.mealmanagement.ui.theme.BlackText
import com.example.mealmanagement.ui.theme.GreenBackGround2
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.inter_bold
import com.example.mealmanagement.ui.theme.inter_light
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.FoodViewModel
import com.example.mealmanagement.viewmodel.MealViewModel
data class MealItem(val name: String, val calories: String)
data class FoodDetail(
    val food: FoodData,
    val detailMealData: DetailMealData
)
data class DetailMeal(
    val foodID: FoodData,
    val detailMealData: DetailMealData
)
@Composable
fun DetailMeal(navController: NavController,idMenu:String,day:String,mealModel:MealViewModel,detailMealViewModel:DetailMealViewModel,foodViewModel: FoodViewModel) {

    val mealList by mealModel.getMealsByMenuId(idMenu).observeAsState(initial = emptyList())
    val mealListDay = mealList.filter { it.day.toString() == day }
    val mealTypes = listOf("Breakfast","Lunch","Dinner")

    var conText = LocalContext.current
    // Kiểm tra và tạo meal nếu cần
    LaunchedEffect(mealList) {
        mealTypes.forEach { mealType ->
            val exists = mealList.any { it.name == mealType && it.day.toString() == day }
            if (!exists) {
//                mealModel.saveMeal(
//                    MealData(
//                        idMeal = "", // Generate a new ID if necessary
//                        idMenu = idMenu,
//                        day = day.toInt(),
//                        name = mealType
//                    ),
//                    conText
//                )
                Log.d("DetailMeal", "Created new meal type: $mealType for day: $day")
            }
        }
    }




    BaseScreen(navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            BannerItem(98,R.drawable.banner_2,"Thực đơn hôm nay",26,navController)
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {

                mealTypes.forEach { mealType ->
                    item {
                        MealTypeSection(
                            mealType = mealType,
                            day = day,
                            mealList = mealList,
                            idMenu = idMenu,
                           mealModel = mealModel,
                            detailMealViewModel = detailMealViewModel,
                            foodViewModel = foodViewModel,
                            navController = navController,
                            conText = conText
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp) )
                }
            }

        }
    }
}
@Composable
fun MealTypeSection(

    mealType: String,
    day: String,
    mealList: List<MealData>,
    idMenu: String,
    mealModel: MealViewModel,
    detailMealViewModel: DetailMealViewModel,
    foodViewModel: FoodViewModel,
    navController: NavController,
    conText: Context
) {



    TimeMeal(time =mealType)
    var mealId = ""
    mealList.forEach( {
        if(it.name == mealType && it.day.toString() == day) {
            mealId = it.idMeal
            val detailMealList by detailMealViewModel.getDetailMealsByMealId(mealId).observeAsState(initial = emptyList())
            var foodList = mutableListOf<FoodDetail>()
            detailMealList.forEach { detailMeal ->
                val food by foodViewModel.getFoodById(detailMeal.idFood).observeAsState(initial = null)
                food?.let {
                    foodList.add(FoodDetail(it, DetailMealData(detailMeal.idDetailMeal, detailMeal.idFood, detailMeal.idMeal, detailMeal.quantity)))
                }
            }
            ListTest(navController,foodList,mealId,detailMealViewModel)
        }


    })
    if(mealId == "") {





    }else {
        ButtonPlus{
            navController.navigate("findFood/${mealId}")
        }

    }



}

@Composable
fun ListTest( navController: NavController,mealList: List<FoodDetail>,mealId:String,detailMealViewModel: DetailMealViewModel) {
    Column {

        mealList.forEach { meal ->

            TimeMealItem(meal.food.name,meal.food.totalCalo.toString(),meal.detailMealData.quantity) {
                navController.navigate("detailMyFood/${meal.food.idFood}/${mealId}/${meal.detailMealData.idDetailMeal}/${meal.detailMealData.quantity}")
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
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
fun TimeMealItem(nameOfFood:String,calo:String,quantity: Int,onClick:()->Unit ){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(BorderStroke(1.2.dp, Color.Black), shape = RoundedCornerShape(12.dp)),
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(GreenBackGround2),
            shape = RoundedCornerShape(14.dp),
            contentPadding = PaddingValues(10.dp, 16.dp),
            onClick = { onClick() }){
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
                        text = quantity.toString()+" cái",
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
