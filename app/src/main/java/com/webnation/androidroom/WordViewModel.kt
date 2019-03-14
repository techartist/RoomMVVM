package com.webnation.androidroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.webnation.androidroom.model.Word
import com.webnation.androidroom.model.WordRepository
import com.webnation.androidroom.model.WordRoomDatabase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.system.measureTimeMillis


class WordViewModel(application: Application) : AndroidViewModel(application) {

        private var parentJob = Job()
        private val coroutineContext: CoroutineContext
            get() = parentJob + Dispatchers.Main
        private val scope = CoroutineScope(coroutineContext)

        private val repository: WordRepository
        val allWords: LiveData<List<Word>>

        init {
            val wordsDao = WordRoomDatabase.getDatabase(application).wordDao()
            repository = WordRepository(wordsDao)
            allWords = repository.allWords
        }

        fun insert(word: Word) = scope.launch(Dispatchers.IO) {
            repository.insert(word)
        }

        fun deleteAllWords() = scope.launch(Dispatchers.IO) {
            repository.deleteAllWords()
        }

        suspend fun countAllWords() : Int = scope.async(Dispatchers.IO) { repository.countAllWords() }.await()

        fun populateDatabase() = scope.launch(Dispatchers.IO) {
            repository.populateDatabase()
        }

        override fun onCleared() {
            super.onCleared()
            parentJob.cancel()
        }
}

