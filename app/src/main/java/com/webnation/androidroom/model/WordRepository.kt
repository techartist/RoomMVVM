package com.webnation.androidroom.model

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.*


class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords()

    @WorkerThread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }


    @WorkerThread
    suspend fun deleteAllWords() {
        wordDao.deleteAll()
    }

    @WorkerThread
    suspend fun countAllWords() : Int {
        return wordDao.getCountOfWords()
    }

    @WorkerThread
    suspend fun populateDatabase() {
        wordDao.deleteAll()

        var word = Word("Hello")
        wordDao.insert(word)
        word = Word("World!")
        wordDao.insert(word)
    }

}
