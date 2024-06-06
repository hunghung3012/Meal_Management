package com.example.mealmanagement.food

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.menu.TextInter
import com.example.mealmanagement.model.MenuData
import com.example.mealmanagement.ui.theme.GrayText
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.PinkBackGround
import com.example.mealmanagement.ui.theme.PinkText
import com.example.mealmanagement.viewmodel.MenuViewModel

import java.time.format.DateTimeFormatter
import java.util.Locale
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ListDay(navController: NavController,menuViewModel: MenuViewModel,menuID:String) {
val menu by menuViewModel.getMenuById(menuID).observeAsState(initial = null)

    BaseScreen(navController) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.Center,

            ) {
                TextInter(text ="Chọn thứ", color = GreenText, size = 18)
            }
            Spacer(modifier = Modifier.height(3.dp))
            ListDayComponent(navController,menuID)
            Spacer(modifier = Modifier.height(100.dp))
            //helo

            menu?.let {
                // Sử dụng menu nếu không null
                NameItem(navController,it, menuViewModel)
            } ?: run {
                // Hiển thị thông báo nếu menu là null
                Text(text = "Menu data is loading or not available")
            }
        }
       

    }
}
@Composable
fun ListDayComponent(navController: NavController,menuID:String) {
    Column(

    ) {
        val days = (2..7).toList()
        val arrDay = listOf<String> (
            "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7", "Chủ Nhật"
        )
        // Sử dụng forEach để lặp qua danh sách và gọi DayItem cho từng phần tử
        //use index to get the day of the week
        arrDay.forEachIndexed  { index,day ->
            DayItem(day,index.toString(),navController,menuID)
        }

    }
    }

@Composable
fun DayItem(day:String,index:String,navController: NavController,menuID:String) {
    Button(
        contentPadding = PaddingValues(15.dp, 16.dp),
        shape = RoundedCornerShape(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        border = BorderStroke(1.dp, Color(229, 229, 229)),
        onClick = {
            navController.navigate("detailMeal/${menuID}/${index}")

        }) {

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
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NameItem(navController: NavController,menu: MenuData, menuViewModel: MenuViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var context = LocalContext.current
    var thucDonName by remember { mutableStateOf(menu.TenThucDon) }

    Row (verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ){
        OutlinedTextField(
            value = thucDonName,
            modifier = Modifier.width(200.dp).padding(5.dp),
            maxLines = 1,
            onValueChange = { thucDonName = it }
        )
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                menu.TenThucDon = thucDonName
                menuViewModel.updateMenu(menu)
                makeToast(context)
                keyboardController?.hide()
            }) {
            Icon(painter = painterResource(id =  R.drawable.baseline_check_circle_24),
                contentDescription = null ,
                modifier = Modifier.size(40.dp),
                tint = GreenText)
        }
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
            contentPadding = PaddingValues(0.dp),
            onClick = {
                menuViewModel.deleteMenu(menu.IDThucDon,context)
                navController.navigate("listMeal")

            }) {
            Icon(painter = painterResource(id =  R.drawable.baseline_remove_circle_24),
                contentDescription = null ,
                modifier = Modifier.size(40.dp),
                tint = PinkText)
        }
    }
}
fun makeToast(context:Context) {
    Toast.makeText(context, "Successfully saved data", Toast.LENGTH_SHORT).show()
}