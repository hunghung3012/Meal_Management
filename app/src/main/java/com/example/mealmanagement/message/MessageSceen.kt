package com.example.mealmanagement.message


import MessageData
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mealmanagement.R
import com.example.mealmanagement.session.session
import com.example.mealmanagement.ui.theme.GreenText
import com.example.mealmanagement.viewmodel.MessageViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessagingScreen(navController: NavController,messageViewModel: MessageViewModel) {
    var receiverId = "test1@gmail.com"
    var senderId = "123"
    if(session.data == "test1@gmail.com") {
        receiverId = "123"
        senderId = "test1@gmail.com"
    }
    if(session.data == "123") {
        receiverId = "test1@gmail.com"
        senderId = "123"
    }



    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    val messages by messageViewModel.getMessagesByUserIds(senderId, receiverId).observeAsState(emptyList())
    var context = LocalContext.current
Log.e("mess","$messages")
Box() {
    Column(modifier = Modifier
        .align(Alignment.TopStart)
        .fillMaxSize()
        .padding(8.dp)) {
        Row {
           IconButton(onClick = {
               navController.popBackStack()
           }) {
               Icon(
                   painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                   contentDescription = "Back",
                   tint = GreenText
               )
           }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Tin nhắn($receiverId)",
                color = GreenText,
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.weight(1f))
        }
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(messages) { message ->
                if (message.idSender == senderId) {
                    SenderMessageItem(message.message)
                } else {
                    ReceiverMessageItem(message.message)
                }
            }
        }


    }
    Row(modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.BottomEnd), verticalAlignment = Alignment.CenterVertically) {
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            modifier = Modifier.weight(1f),
            placeholder = { Text("Nhập tin nhắn...") }
        )
        Button(
            onClick = {
                if (messageText.text.isNotEmpty()) {
                    val currentDateTime = LocalDateTime.now()
                    val formatter = DateTimeFormatter.ISO_DATE_TIME
                    val formattedDateTime = currentDateTime.format(formatter)
                    val newMessage = MessageData(
                        idMessage = "",
                        idSender = senderId,
                        idReceiver = receiverId,
                        message = messageText.text,
                        time = formattedDateTime,
                        isSender = true
                    )
                        messageViewModel.saveMessage(newMessage, context)
                    Log.e("sleep","$formattedDateTime")
                    messageText = TextFieldValue("")
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Gửi")
        }
    }
}

}

@Composable
fun SenderMessageItem(text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .border(
                width = 2.dp,
                color = Color.Transparent,
                shape = RoundedCornerShape(30.dp) // Làm viền tròn
            ),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = text,
            modifier = Modifier

                .background(Color(37, 150, 190))
                .padding(8.dp)
                .border(
                    width = 2.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(8.dp) // Làm viền tròn
                )
                ,
            color = Color.White,
            fontSize = 16.sp
        )
//        Icon(
//            imageVector = Icons.Default.Face,
//            contentDescription = "",
//            modifier = Modifier.size(30.dp),
//            tint = GreenText
//
//        )
    }
}

@Composable
fun ReceiverMessageItem(text: String) {
    Row(
        modifier = Modifier

            .padding(4.dp)


            ,
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "",
            modifier = Modifier.size(35.dp),
            tint = Color(181, 177, 176)

        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            modifier = Modifier
                .background(Color(220, 227, 233))
                .padding(8.dp)
                .border(2.dp, Color.Transparent, shape = RoundedCornerShape(30.dp)), // Bo góc với radius 8.dp
            color = Color(31, 29, 28),
            fontSize = 16.sp
        )
    }
}

data class Message(val text: String, val isSender: Boolean)