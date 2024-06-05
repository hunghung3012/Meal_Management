package com.example.mealmanagement.food

import android.graphics.drawable.shapes.RoundRectShape
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.menu.Menu
import com.example.mealmanagement.menu.TextInter
import com.example.mealmanagement.model.MenuData
import com.example.mealmanagement.ui.theme.GrayText
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
fun ListDay(navController: NavController,menuViewModel: MenuViewModel,menuID:String) {
    val menu by menuViewModel.getMenuById(menuID).observeAsState(initial = null)
    BaseScreen {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Center,

            ) {
                TextInter(text = "Chọn thứ", color = GreenText, size = 18)
            }
            Spacer(modifier = Modifier.height(3.dp))
//            ListDayComponent()
            Spacer(modifier = Modifier.height(100.dp))

//            NameItem(menu!!,menuViewModel)
        }
       

    }
}
@Composable
fun ListDayComponent() {
    Column(

    ) {
        val days = (2..7).toList()
        // Sử dụng forEach để lặp qua danh sách và gọi DayItem cho từng phần tử
        days.forEach { day ->
            DayItem("Thứ "+day.toString())
        }
        DayItem("Chủ Nhật")
    }
    }

@Composable
fun DayItem(day:String) {
    Button(
        contentPadding = PaddingValues(15.dp, 16.dp),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, Color(229, 229, 229)),
        onClick = { /*TODO*/ }) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()

        ) {
            TextInter(text = "$day", color = GrayText , size = 18)
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24)
                , contentDescription = "",
                tint = GrayText,
                modifier = Modifier.size(23.dp),
            )
        }
    }
   

}
@Composable
fun NameItem(menu:MenuData, menuViewModel: MenuViewModel) {
    var thucDonName by remember { mutableStateOf(menu.TenThucDon) }

    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            value = thucDonName,
            modifier = Modifier.width(150.dp).padding(5.dp),
            maxLines = 1,
            onValueChange = { thucDonName = it }
        )
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                menu.TenThucDon = thucDonName
                menuViewModel.updateMenu(menu)
            }) {
            Icon(painter = painterResource(id =  R.drawable.baseline_check_circle_24),
                contentDescription = null ,
                modifier = Modifier.size(40.dp),
                tint = GreenText)
        }
    }
}