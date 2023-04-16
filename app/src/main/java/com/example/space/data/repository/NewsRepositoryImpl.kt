package com.example.space.data.repository

import android.util.Log
import com.example.space.data.KeysMap
import com.example.space.data.NameTables
import com.example.space.data.model.News
import com.example.space.domain.convertNewsToMap
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class NewsRepositoryImpl : NewsRepository {

    companion object {
        const val TAG = "Repository"
    }

    private val db = Firebase.firestore

    override suspend fun getNews(): List<News> = withContext(Dispatchers.IO) {
        val news: MutableList<News> = mutableListOf()
        db.collection(NameTables.News.name)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                for (doc in documentSnapshot) {
                    news.add(convert(doc.id, doc.data))
                }
            }
            .addOnFailureListener { e ->
                Log.i(TAG, "error $e")
            }
        return@withContext news
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
    private fun convert(id: String, map: MutableMap<String, Any>): News {
        return News(
            id,
            map[KeysMap.LABEL.name] as String,
            map[KeysMap.DATE.name] as Long,
            map[KeysMap.TEXT.name] as String
        )
    }
}