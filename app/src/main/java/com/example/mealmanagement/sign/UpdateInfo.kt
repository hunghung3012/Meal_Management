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
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import com.example.mealmanagement.model.UserData
import com.example.mealmanagement.session.session
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.viewmodel.UserViewModel

@Composable
fun UpdateInfo(navController: NavController, userViewModel: UserViewModel) {
    val user = userViewModel.getUserByUsername(session.data).observeAsState().value ?: UserData()
    var height by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),

    ) {
        Button(

            onClick = {
                navController.navigateUp()
            }
        ) {
           Text(text = "Quay lại")
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedTextField(
                value = height,
                onValueChange = { height = it },
                placeholder = { Text(text = "Chiều cao") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = weight,
                onValueChange = { weight = it },
                placeholder = { Text(text = "Cân nặng") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    user.height = height.toDoubleOrNull() ?: 0.0
                    user.weight = weight.toDoubleOrNull() ?: 0.0
                    if(user.height == 0.0 || user.weight == 0.0){
                        showAlertDialog(context, "Error", "Nhập đúng thông tin")
                    }
                    else {
                        userViewModel.updateUser(user,context)
                        navController.navigateUp()
                    }


                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Xác nhận", fontSize = 16.sp)
            }
        }
    }}