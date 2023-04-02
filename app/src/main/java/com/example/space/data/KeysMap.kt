package com.example.space.data

sealed class KeysMap(val name: String){
    object LABEL: KeysMap("label")
    object DATE: KeysMap("date")
    object TEXT: KeysMap("text")
}