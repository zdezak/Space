package com.example.space.data

sealed class NameTables(val name: String){
    object News: NameTables("News")
    object Payment: NameTables("Payment")
}
