package com.example.mealmanagement.food

import android.widget.Button
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.ui.theme.BlackText
import com.example.mealmanagement.ui.theme.GreenBackGround
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.PinkBackGround
import com.example.mealmanagement.ui.theme.PinkText
import com.example.mealmanagement.viewmodel.DetailMealViewModel
import com.example.mealmanagement.viewmodel.MealViewModel

@Composable
fun AddFood(navController: NavController,mealId:String,mealViewModel: MealViewModel,detailMealViewModel:DetailMealViewModel) {
    BaseScreen(navController) {
        Column {
//            BannerItem(height = 240, img = R.drawable.banner_3, text = "", fontSize =0 )
            LazyColumn (
                modifier = Modifier.padding(20.dp)
            )
            {
                item {
                    DetailFoodToAdd("Tên*","short")
                }
                item {
                    DetailFoodToAdd("Tổng Calo","short")
                }
                item {
                    DetailFoodToAdd("Thành Phần*","long")
                }
                item {
                    DetailFoodToAdd("Cách làm","long")
                }
                item {
                    DetailFoodToAdd("Địa chỉ","long")
                }
                item {
                    ButtonAdd()
                }

                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }





            }
        }

    }

}
@Composable
fun DetailFoodToAdd(bold:String,length:String) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    if(length == "long") {
        Column {
            Text(text = bold+": ",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.width(130.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                value = searchQuery,
                onValueChange = { searchQuery = it })

        }
    }
    else if(length == "short")  {
        Row(
           verticalAlignment = Alignment.CenterVertically
                ){
            Text(text = bold+": ",
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                modifier = Modifier.width(100.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                value = searchQuery,
                onValueChange = { searchQuery = it })

        }
    }

    Spacer(modifier = Modifier.height(10.dp))
}
@Composable
fun ButtonAdd() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        
        Button(

            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = GreenBackGround),
            onClick = { /*TODO*/ }){
            Text(text = "Thêm",
                color = BlackText
            )

        }
    }

}