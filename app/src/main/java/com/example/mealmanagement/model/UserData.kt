package com.example.mealmanagement.model

data class UserData(
    var user:String = "",
    var pass:String = "",
    var name:String = "",
    var phone:String = "",
    var height:Double = 0.0,
    var weight:Double = 0.0,
    var bmi:Double = getBMI(height,weight)
)
fun getBMI(height:Double,weight:Double):Double{
    if(height == 0.0 || weight == 0.0)
        return 0.0
    else return weight/(height*height)
}
