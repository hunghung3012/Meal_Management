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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.menu.ButtonBack
import com.example.mealmanagement.menu.Menu
import com.example.mealmanagement.ui.theme.GreenBackGround
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.OrangeBackGround
import com.example.mealmanagement.ui.theme.OrangeText
import com.example.mealmanagement.ui.theme.PinkBackGround
import com.example.mealmanagement.ui.theme.PinkText
import com.example.mealmanagement.ui.theme.inter_bold
import com.example.mealmanagement.ui.theme.inter_extrabold
import com.example.mealmanagement.ui.theme.inter_light
import com.example.mealmanagement.ui.theme.inter_medium
import com.example.mealmanagement.ui.theme.kanit_bold
import com.example.mealmanagement.ui.theme.kanit_ragular
import java.nio.file.WatchEvent
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
@Composable
fun MyFood() {
    BaseScreen {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BannerItem(100,R.drawable.banner_2,"Món Ăn Của Bạn",23)
            Spacer(modifier = Modifier.height(10.dp))
            SelectMyMeal()
            Spacer(modifier = Modifier.height(10.dp))
            ButtonPlus()
        }
    }
}



@Composable
fun  SelectMyMeal(){
    SingleMyMeal()
    Spacer(modifier = Modifier.height(10.dp))
    SingleMyMeal()
    Spacer(modifier = Modifier.height(10.dp))
    SingleMyMeal()


}
@Composable
fun SingleMyMeal() {
    Button(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = ButtonDefaults.buttonColors(OrangeBackGround),
        contentPadding = PaddingValues(10.dp, 16.dp),
        onClick = { /*TODO*/ }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_fastfood_24)
                , contentDescription = "",
                tint = OrangeText,
                modifier = Modifier.size(25.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Thực đơn 1",
                color = OrangeText,
                fontSize = 20.sp,
                fontFamily = inter_bold,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,

                )
            Spacer(modifier = Modifier.width(180.dp))
            Icon(
                painter = painterResource(id = R.drawable.baseline_arrow_forward_ios_24)
                , contentDescription = "",
                tint = OrangeText,
                modifier = Modifier.size(23.dp),
            )


        }
    }

}
