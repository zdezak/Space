package com.example.space.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.space.data.KeysMap
import com.example.space.data.NameTables
import com.example.space.data.model.News
import com.example.space.data.repository.NewsRepositoryImpl
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    private val repository = NewsRepositoryImpl()
    private val db = Firebase.firestore
    private val list = mutableListOf<News>()

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>>
        get() = _news

    init {
        viewModelScope.launch {
            getNews()
        }
    }
    private fun getNews(){
        db.collection(NameTables.News.name)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                for (doc in documentSnapshot) {
                    list.add(convert(doc.id, doc.data))
                }
                _news.value = list
            }
            .addOnFailureListener { e ->
                Log.i(NewsRepositoryImpl.TAG, "error $e")
            }
    }

    private fun convert(id: String, map: MutableMap<String, Any>): News {
        return News(
            id,
            map[KeysMap.LABEL.name] as String,
            map[KeysMap.DATE.name] as Long,
            map[KeysMap.TEXT.name] as String
        )
    }

}