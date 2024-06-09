package com.example.mealmanagement.sign

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
import com.example.mealmanagement.model.FoodData
import com.example.mealmanagement.model.UserData
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.viewmodel.UserViewModel

@Composable
fun SignIn(navController: NavController, userviewModel: UserViewModel,user:String = "" ,password:String="") {
    var username by remember { mutableStateOf(user) }
    var pass by remember { mutableStateOf(password) }
    var context = LocalContext.current
    val userData by userviewModel.getUserByUsername(username).observeAsState()

    var passwordVisible by remember { mutableStateOf(false) }
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
        Text(text = "Sign In",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(142, 87, 87),
            modifier = Modifier.padding(vertical = 30.dp))
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = username,
            onValueChange = {
                username= it
            },
            placeholder = { Text(text = "Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = {
                pass= it
            },
            placeholder = { Text(text = "Password") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(id = R.drawable.baseline_vpn_key_off_24)

                else
                    painterResource(id = R.drawable.baseline_remove_red_eye_24)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Image(painter = image, contentDescription = null)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {

                // check sign in


                if(userData?.pass == pass) {
                    navController.navigate("editInfo/${userData?.user}")
                }
                else {
                    showAlertDialog(context ,"Error","Tài khoản hoặc mật khẩu sai")
                }

            },
            shape = RoundedCornerShape(14.dp),
            modifier = Modifier.fillMaxWidth().height(50.dp)
        ) {
            Text(text = "Sign in")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Forgot Password?")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "OR", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Facebook Login",
                    modifier = Modifier.size(40.dp)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.gmail),
                    contentDescription = "Gmail Login",
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Text(text = "Don't Have An Account? ")
            ClickableText(
                text = AnnotatedString("Sign up"),
                onClick = {
                    navController.navigate("signUp")
                },
                style = LocalTextStyle.current.copy(color = GreenText, fontWeight = FontWeight.Bold)
            )
        }
    }

}
