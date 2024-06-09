package com.example.mealmanagement.model

data class FoodData(
    var idFood:String = "",
    var name:String = "",
    var element:String = "",
    var img:String = "",
    var totalCalo:Double =0.0,
    var method:String = "",
    var address:String = "",
    var idUser:String = "",
    var allow:Boolean = false
)
