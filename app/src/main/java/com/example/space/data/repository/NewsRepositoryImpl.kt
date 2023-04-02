package com.example.space.data.repository

import android.util.Log
import com.example.space.data.KeysMap
import com.example.space.data.NameTables
import com.example.space.data.model.News
import com.example.space.domain.convertNewsToMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.ZoneOffset

class NewsRepositoryImpl : NewsRepository {

    companion object {
        const val TAG = "Repository"
    }

    private val newsDefault = mutableListOf(
        News(
            "Отключение воды 20.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение 20.03.2023 г."
        ),
        News(
            "Плановое отключение электричествоа 21.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение электричествоа 21.03.2023 г."
        ),
        News(
            "Отключение воды 22.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение 22.03.2023 г."
        ),
        News(
            "Плановое отключение электричествоа 23.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение электричествоа 23.03.2023 г."
        ),
        News(
            "Отключение воды 24.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение 24.03.2023 г."
        ),
        News(
            "Плановое отключение электричествоа 30.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение электричествоа 30.03.2023 г."
        ),
        News(
            "Плановое отключение электричествоа 31.03.2023",
            LocalDateTime.now().atZone(ZoneOffset.UTC).toEpochSecond(),
            "Плановое отключение электричествоа 31.03.2023 г."
        )
    )
    private val db = Firebase.firestore

    override suspend fun getNews(): List<News> {
        val news: MutableList<News> = mutableListOf()
        db.collection(NameTables.News.name)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                for (doc in documentSnapshot) {
                    news.add(convert(doc.data))
                }
            }
            .addOnFailureListener { e ->
                Log.i(TAG, "error $e")
            }
        return news
    }

    override suspend fun setNews(newsList: List<News>) {
        for (news in newsList) {
            db.collection(NameTables.News.name)
                .add(news.convertNewsToMap())
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }
    }

    //Map<String, Any?>.convert()
    private fun convert(map: MutableMap<String, Any>): News {
        return News(
            map[KeysMap.LABEL.name] as String,
            map[KeysMap.DATE.name] as Long,
            map[KeysMap.TEXT.name] as String
        )
    }
}