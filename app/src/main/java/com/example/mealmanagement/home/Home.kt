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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
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
import com.example.mealmanagement.ui.theme.inter_medium
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
fun Home(navController: NavController) {

    BaseScreen(navController) {
        Column {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
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
            InforCalo()

        }
            InforHealthy()
        }

    }
}
@Composable
fun InforCalo() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InforCalo_Item("2")
        Column(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(54, 155, 113)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(
                text = " 1256\n/2012",
                lineHeight = 40.sp,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(255, 226, 249),
                fontFamily = inter_medium
            )

        }
        InforCalo_Item("2")
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
fun InforHealthy() {
    Row(
        modifier = Modifier.padding(30.dp,20.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(251, 236, 208))
                .height(200.dp)
                .weight(1.5f)
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
            InforHealthyItem()
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
fun InforHealthyItem() {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        InforHealthyItemButton("Cân Nặng", 60,"kg")
        InforHealthyItemButton("Chiều Cao", 172,"cm")
        InforHealthyItemNoButton("25.7")
    }
}
@Composable
fun InforHealthyItemButton(name:String , value:Int, donvi:String) {

        Row(

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.height(50.dp)
        ) {
            Text(text = "$name:$value$donvi",fontSize = 18.sp)
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues(0.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    tint = Color(226, 128, 74),
                    painter = painterResource(id = R.drawable.baseline_add_circle_24),
                    contentDescription ="" )
            }
        }


}
@Composable
fun InforHealthyItemNoButton(bmi:String) {

    Row(
        modifier = Modifier.height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text ="Chỉ số BMI: ",
            fontSize = 18.sp)
        Text(text = "$bmi",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)

    }


}