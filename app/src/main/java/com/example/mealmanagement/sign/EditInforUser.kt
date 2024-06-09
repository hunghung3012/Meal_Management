package com.example.mealmanagement.sign


import android.app.AlertDialog
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.model.UserData
import com.example.mealmanagement.session.session
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.viewmodel.UserViewModel

@Composable
fun EditInforUser(navController: NavController, userViewModel: UserViewModel,username:String) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }

//    val userData by userViewModel.getUserByUsername(username).observeAsState()
    val userData = userViewModel.getUserByUsername(username).observeAsState().value ?: UserData()
    if(userData.name != "") {
        navController.navigate("home")
    }
    var context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(255,255,255))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner_4),
            contentDescription = "Banner",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(15.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Thông tin của bạn",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(142, 87, 87),
            modifier = Modifier.padding(vertical = 30.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = {
                name= it
            },
            placeholder = { Text(text = "Tên của bạn") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = phone,
            onValueChange = {
                phone= it
            },
            placeholder = { Text(text = "Số điện thoại") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = height.toString(),
            onValueChange = {
                height= it
            },
            placeholder = { Text(text = "Chiều cao (cm)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = weight.toString(),
            onValueChange = {
                weight= it
            },
            placeholder = { Text(text = "Cân nặng (kg)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(8.dp))



        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // add User
                if(name == ""  ) {
                    showAlertDialog(context, "Error", "Không được để rỗng các thông tin")
                }
                else if(height.toDoubleOrNull() == null || weight.toDoubleOrNull() == null) {
                    showAlertDialog(context, "Error", "Chỉ được nhập số tại các trường cân nặng, chiều cao")
                }
                else if(!isValidPhoneNumber(phone)) {
                    showAlertDialog(context, "Error", "Nhập sai định dạng điện thoại")
                }
                else {
                    userData.name = name
                    userData.phone = phone
                    userData.weight = weight.toDoubleOrNull()!!
                    userData.height = height.toDoubleOrNull()!!
                    userViewModel.updateUser(userData, context)
                    session.data = userData.user
                    navController.navigate("home")
                }



            },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = "Xác nhận")
        }


        Spacer(modifier = Modifier.height(50.dp))


        }

}
fun isValidPhoneNumber(phone: String): Boolean {
    return phone.length == 10 && phone.all { it.isDigit() }
}