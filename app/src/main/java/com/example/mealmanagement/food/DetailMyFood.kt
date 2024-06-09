package com.example.mealmanagement.food



import android.util.Log
import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.model.DetailMealData
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.ui.theme.BlackText
import com.example.mealmanagement.ui.theme.GreenBackGround
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.PinkBackGround
import com.example.mealmanagement.ui.theme.PinkText
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.FoodViewModel


@Composable
fun DetailMyFood(navController: NavController,detail:DetailMealData,foodViewModel: FoodViewModel,detailMealViewModel: DetailMealViewModel) {
    val food = foodViewModel.getFoodById(detail.idFood).observeAsState().value ?: FoodData()
    var context = LocalContext.current
    var count by remember { mutableStateOf(detail.quantity) }
    BaseScreen(navController) {
        Column {
            BannerItem(height = 240, img = R.drawable.banner_3, text = "", fontSize =0,navController )
            LazyColumn(
                modifier = Modifier.padding(20.dp)
            ) {

                item {
                    DetailFoodItem2("Tên",food.name)

                }
                item {
                    DetailFoodItem2("Lượng calo","${food.totalCalo} kcal")

                }
                item {
                    DetailFoodItem2("Thành phần",
                        "${formatCookingMethod(food.element)}"
                    )

                }
                item {

                    DetailFoodItem2("Cách chế biến",
                        "${formatCookingMethod(food.method)}"
                    )
                }
                item {


                }
                item {
                    DetailFoodItem2("Địa chỉ",
                        "${food.address}"
                    )

                }
                item {
                    ButtonChange2(
                        navController,
                        count,
                        {count++},
                        {if(count>1)  count--},
                        {
                            //aupdate to cart
                            var detailMealItem = DetailMealData(detail.idDetailMeal,detail.idMeal,detail.idFood,count)
                            detailMealViewModel.updateDetailMeal(detailMealItem,context)
                            navController.navigateUp()


                        },
                        {
                            //remove from cart
                            detailMealViewModel.deleteDetailMealsByID(detail.idDetailMeal,context)
                            navController.navigateUp()

                        }
                    )


                }
                item {
                    Spacer(modifier = Modifier.height(200.dp))
                }







            }
        }

    }

}
@Composable
fun DetailFoodItem2(bold:String,detail:String) {
    Row {
        Text(text = bold+": ",
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp,
            modifier = Modifier.width(100.dp)
        )
        Text(text = detail,
            fontSize = 17.sp)
    }
    Spacer(modifier = Modifier.height(10.dp))
}
@Composable
fun ButtonChange2(
    navController: NavController,
    count:Int,
    plus:()->Unit,
    minus:()->Unit,
    update:()->Unit,
    remove:()->Unit
) {

    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(value = count.toString(),
                onValueChange = {

                },
                modifier = Modifier.width(40.dp),

                )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Button(
                    modifier = Modifier.size(25.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenText),
                    onClick = { plus()}) {
                    Icon(imageVector =  Icons.Default.KeyboardArrowUp, contentDescription =null )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Button(
                    modifier = Modifier.size(25.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenText),
                    onClick = { minus() }) {
                    Icon(imageVector =  Icons.Default.KeyboardArrowDown, contentDescription =null )
                }
            }
        }

        Row {
            Button(

                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkText),
                onClick = {
                    update()

                }){
                Text(text = "Câp nhật",
                    color = BlackText)

            }
            Button(

                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkBackGround),
                onClick = {
                remove()
                }){
                Text(text = "Xóa",
                    color = BlackText)

            }
        }

    }

}
