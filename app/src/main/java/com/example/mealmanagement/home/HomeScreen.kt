package com.example.mealmanagement.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.menu.Menu
import java.nio.file.WatchEvent


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BaseScreen(navController: NavController,component: @Composable () -> Unit) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = Color(233, 245, 236),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(15.dp))


            ) {
              Menu(Modifier,navController)
            }
        }
    ) {
        component()
    }
}