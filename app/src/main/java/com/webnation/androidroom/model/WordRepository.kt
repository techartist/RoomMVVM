package com.webnation.androidroom.model

import android.app.Application
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*


class WordRepository(application: Application) {

    private val mWordDao : WordDao?
    val allWords: LiveData<List<Word>>?
    val job = Job()

    val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    init {
        val db = WordRoomDatabase.getDatabase(application,coroutineScope)
        mWordDao = db.wordDao()
        allWords = mWordDao.allWords
    }


    fun insert(word: Word) {
        insertWord(word)
    }

    fun insertWord(word : Word) {
        coroutineScope.launch {
            mWordDao?.insert(word)
        }
    }

    fun deleteAllWords() {
        coroutineScope.launch {
            mWordDao?.deleteAll()
        }
    }


}
