package com.example.mealmanagement.food

import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.menu.ButtonBack
import com.example.mealmanagement.menu.Menu
import com.example.mealmanagement.model.MenuData
import com.example.mealmanagement.ui.theme.GreenBackGround
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.PinkBackGround
import com.example.mealmanagement.ui.theme.PinkText
import com.example.mealmanagement.ui.theme.inter_bold
import com.example.mealmanagement.ui.theme.inter_extrabold
import com.example.mealmanagement.ui.theme.inter_light
import com.example.mealmanagement.ui.theme.inter_medium
import com.example.mealmanagement.ui.theme.kanit_bold
import com.example.mealmanagement.ui.theme.kanit_ragular
import com.example.mealmanagement.viewmodel.MenuViewModel
import java.nio.file.WatchEvent
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
@Composable
fun ListMeal(navController: NavController,menuViewModel: MenuViewModel, userId: String) {
    val thucDonList by menuViewModel.getThucDonByUserId(userId).observeAsState(initial = emptyList())
    val context = LocalContext.current

    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(thucDonList) {
        isLoading = thucDonList.isEmpty()
    }

    BaseScreen(navController) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BannerItem(148,R.drawable.banner_2,"Tổng số: ${thucDonList.size} thực đơn",23,navController)
            Spacer(modifier = Modifier.height(10.dp))
            SelectButton()
            Spacer(modifier = Modifier.height(10.dp))
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    items(thucDonList) { thucDon ->
                        SingleMeal(thucDon, navController)
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }

            ButtonPlus{
                // Tìm tên thực đơn có số thứ tự cao nhất
                val lastThucDonNumber = thucDonList
                    .mapNotNull { it.TenThucDon.removePrefix("Thực đơn ").toIntOrNull() }
                    .maxOrNull() ?: 0

                // Tạo tên thực đơn mới
                val newThucDonNumber = lastThucDonNumber + 1
                val newThucDonName = "Thực đơn $newThucDonNumber"

                // Tạo mới một MenuData với tên mới
                val newThucDon = MenuData("", userId, newThucDonName, "2023-01-01")

                // Lưu thực đơn mới vào cơ sở dữ liệu
                menuViewModel.saveMenu(newThucDon, context)
            }


        }
    }
}


@Composable
fun BannerItem(
    height:Int,img:Int,text:String,fontSize:Int,
    navController: NavController) {
    Box(
        modifier = Modifier.fillMaxWidth(),

    ) {

        Image(
            painter = painterResource(id = img),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(height.dp)
                .fillMaxWidth()
                .align(Alignment.Center)
                .clip(RoundedCornerShape(15.dp))

        )
        ButtonBack(modifier = Modifier.offset(x = 10.dp, y = 5.dp),navController )
        Text(text =text,
            modifier = Modifier.align(Alignment.Center),
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = kanit_bold,
            color = Color(135, 118, 112)


        )
    }

}
@Composable
fun SelectButton() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
        
    ) {
        SingleButton(GreenText, GreenBackGround,R.drawable.baseline_emoji_people_24) {

        }
        Spacer(modifier = Modifier.width(10.dp))
        SingleButton(PinkText, PinkBackGround,R.drawable.baseline_thumb_up_24) {

        }
    }
   
}
@Composable
fun SingleButton(color:Color,backGround :Color, icon: Int,onclick: () -> Unit) {
    Button(
        shape = RoundedCornerShape(80.dp),
        modifier = Modifier.size(50.dp),
        colors = ButtonDefaults.buttonColors(backGround),
        contentPadding = PaddingValues(0.dp),
        onClick = onclick)
    {
        Icon(painter = painterResource(id =icon)
            , contentDescription = "",
            tint = color
        )
    }
}


@Composable
fun SingleMeal(menu:MenuData,navController: NavController){
    Row {
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(GreenBackGround),
            contentPadding = PaddingValues(10.dp, 16.dp),
            onClick = { navController.navigate("listDay/${menu.IDThucDon}") }) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_fastfood_24)
                    , contentDescription = "",
                    tint = GreenText,
                    modifier = Modifier.size(25.dp),
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = menu.TenThucDon,
                    color = GreenText,
                    fontSize = 20.sp,
                    fontFamily = inter_bold,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.width(270.dp)
                    )

                Icon(
                    painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24)
                    , contentDescription = "",
                    tint = GreenText,
                    modifier = Modifier.size(23.dp),
                )
            }
        }
    }

}
@Composable
fun ButtonPlus(
    onclick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            onClick = onclick
        ) {
            Icon(painter = painterResource(id =  R.drawable.baseline_add_circle_24),
                contentDescription = null ,
                modifier = Modifier.size(40.dp),
                tint = GreenText)

        }
    }

}
