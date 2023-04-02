package com.example.space.domain

import com.example.space.data.KeysMap
import com.example.space.data.model.News

class Convert {
    companion object {
        fun convertLongToDate(date: Long): String {
            return date.toString()
        }
    }
}

fun News.convertNewsToMap(): Map<String, Any> {
    return hashMapOf(
        KeysMap.LABEL.name to label,
        KeysMap.DATE.name to date,
        KeysMap.TEXT.name to text
    )
}