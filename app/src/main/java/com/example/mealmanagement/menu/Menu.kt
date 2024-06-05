package com.example.mealmanagement.menu

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealmanagement.R
import com.example.mealmanagement.ui.theme.GreenBackGround
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.ui.theme.inter_bold
import com.example.mealmanagement.ui.theme.inter_medium


@Composable
fun Menu(modifier: Modifier) {
Column(
    modifier = modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .height(56.dp) ,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        MenuItem( R.drawable.baseline_home_24,Color(112, 116, 113))
        MenuItem( R.drawable.baseline_fastfood_24,Color(112, 116, 113))
        MenuItem( R.drawable.baseline_add_circle_24,Color(62, 168, 89))
        MenuItem( R.drawable.baseline_message_24,Color(112, 116, 113))
        MenuItem( R.drawable.baseline_settings_24,Color(112, 116, 113))
    }
}

    
}
@Composable
fun MenuItem(idIcon:Int, color: Color) {

    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        contentPadding = PaddingValues(0.dp),
        onClick = { /*TODO*/ }) {
        Icon(painter = painterResource(id = idIcon),
            contentDescription = null ,
            modifier = Modifier.size(40.dp),
            tint = color
        )
    }

}
@Composable
fun TextInter(text:String,color:Color, size:Int) {
    Text(

        text = text,
        color = color,
        fontSize = 20.sp,
        fontFamily = inter_bold,
        fontWeight = FontWeight(500),
        )
}
@Composable
fun ButtonBack(
    modifier: Modifier,

) {
    Button(
        modifier= modifier.size(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = GreenText),
        contentPadding = PaddingValues(0.dp),
        onClick = { /*TODO*/ }

    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_arrow_back_24),
            contentDescription = null,
            modifier= Modifier.size(20.dp),
            tint = GreenBackGround

        )
    }
}