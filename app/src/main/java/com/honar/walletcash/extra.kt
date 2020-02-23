package com.honar.walletcash

import android.app.Activity
import android.content.Context
import android.widget.Toast
import java.util.*

data class Cash(
    var id: Int = 0,
    var name: String = "",
    var description: String = "",
    var date: Date,
    var cash: Int = 0,
    var tag: Int = 18
)

val TAG_FOOD_AND_DRINK = 1
val TAG_SHOPPING = 2
val TAG_TRANSPORT = 3
val TAG_HOME = 4
val TAG_BILLS_AND_FEE = 5
val TAG_ENTERTAINMENT = 6
val TAG_CAR = 7
val TAG_CAR_OIL = 8
val TAG_EDUCATION = 9
val TAG_TRAVEL = 10
val TAG_FAMILY_AND_PERSONAL = 11
val TAG_HEALTH = 12
val TAG_GROCERIES = 13
val TAG_GIFT = 14
val TAG_SPORT_AND_HOBBIES = 15
val TAG_BEAUTY = 16
val TAG_WORK = 17
val TAG_other = 18

var itemData = arrayListOf<Cash>()
var showItemData = arrayListOf<Cash>()

fun getImageIDByTAG(tagID: Int): Int {
    return when (tagID) {
        TAG_FOOD_AND_DRINK -> R.drawable.tag_food
        TAG_SHOPPING -> R.drawable.tag_shopping
        TAG_TRANSPORT -> R.drawable.tag_taxi
        TAG_HOME -> R.drawable.tag_home
        TAG_BILLS_AND_FEE -> R.drawable.tag_bill_and_fee
        TAG_ENTERTAINMENT -> R.drawable.tag_entertainment
        TAG_CAR -> R.drawable.tag_car
        TAG_CAR_OIL -> R.drawable.tag_oil
        TAG_EDUCATION -> R.drawable.tag_education
        TAG_TRAVEL -> R.drawable.tag_travl
        TAG_FAMILY_AND_PERSONAL -> R.drawable.tag_family
        TAG_HEALTH -> R.drawable.tag_health
        TAG_GROCERIES -> R.drawable.tag_groceries
        TAG_GIFT -> R.drawable.tag_gift
        TAG_SPORT_AND_HOBBIES -> R.drawable.tag_sport
        TAG_BEAUTY -> R.drawable.tag_beaty
        TAG_WORK -> R.drawable.tag_work
        TAG_other -> R.drawable.tag_other
        else -> R.drawable.tag_other

    }
}


fun ttt(context: Activity,text : String){
    Toast.makeText(context,text,Toast.LENGTH_LONG).show()
}


val NUMnewedstBtn = 0
val NUMoldestBtn = 1
val NUMhighestPrice = 2
val NUMlowestPrice = 3
val NUMType = 4
val NUMDefult = 5