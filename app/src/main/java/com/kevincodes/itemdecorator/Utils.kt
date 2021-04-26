package com.kevincodes.itemdecorator

import androidx.recyclerview.widget.DiffUtil

data class History(
    val id: Int?,                        //0
    val bankName: String,               //BRH
    val conversionType: String,        //USD -> HTG
    val amountConverted: String,      //1 USD
    val conversionResult: String,    //80,40 HTG
    val conversionRateType: String, //Sale rate
    val rateFromDate: String       //08/04/2021
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<History>() {
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
        }
    }
}

val historyList = listOf(
    History(
        0,
        "BRH",
        "USD -> HTG",
        "12 USD",
        "1022.7516 HTG",
        "Purchase rate",
        "26/04/2021"
    ),
    History(
        1,
        "UNIBANK | UNITRANSFER",
        "USD -> HTG",
        "25 USD",
        "2050.0 HTG",
        "Sale rate",
        "26/04/2021"
    )
)