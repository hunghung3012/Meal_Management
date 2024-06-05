package com.example.mealmanagement.food

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealmanagement.R
import com.example.mealmanagement.home.BaseScreen
import com.example.mealmanagement.ui.theme.BlackText
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.PinkBackGround
import com.example.mealmanagement.ui.theme.PinkText

@Composable
fun DetailFood() {
    BaseScreen {
        Column {
            BannerItem(height = 240, img = R.drawable.banner_3, text = "", fontSize =0 )
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                DetailFoodItem("Tên","Hamburger")
                DetailFoodItem("Lượng calo","128 kcal")
                DetailFoodItem("Thành phần",
                      "+ 1 bánh mì không"
                            +"\n+ 1 Miếng thịt bò"
                            +"\n+ 1 Lát Cà Chua"
                )
                DetailFoodItem("Cách chế biến",
                    "Tham khảo tại https://www.youtube.com\n" +
                            "/watch?v=3KQs3EqQ9SA"
                )
                DetailFoodItem("Địa chỉ",
                    "Tìm tại https://www.figma.com/design/jYzWtRxDMEZtPJSaMUwKa5/Do-An?node-id=7-765&t=vJaviP4UEp4aBha5-0"
                )

                ButtonChange()
            }
        }

    }

}
@Composable
fun DetailFoodItem(bold:String,detail:String) {
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
fun ButtonChange() {
    Row(
        Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(value = "1",
                onValueChange = {},
                modifier = Modifier.width(40.dp),

                )
            Spacer(modifier = Modifier.width(5.dp))
            Column {
                Button(
                    modifier = Modifier.size(25.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenText),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector =  Icons.Default.KeyboardArrowUp, contentDescription =null )
                }
                Spacer(modifier = Modifier.height(2.dp))
                Button(
                    modifier = Modifier.size(25.dp),
                    shape = RoundedCornerShape(12.dp),
                    contentPadding = PaddingValues(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = GreenText),
                    onClick = { /*TODO*/ }) {
                    Icon(imageVector =  Icons.Default.KeyboardArrowDown, contentDescription =null )
                }
            }
        }

        Button(

            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PinkBackGround),
            onClick = { /*TODO*/ }){
            Text(text = "Xóa",
                color = BlackText)

        }
    }

}