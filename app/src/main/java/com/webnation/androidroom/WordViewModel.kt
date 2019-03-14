package com.webnation.androidroom

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.webnation.androidroom.model.Word
import com.webnation.androidroom.model.WordRepository
import kotlinx.coroutines.Job


class WordViewModel(application: Application) : AndroidViewModel(application) {
    private val mRepository: WordRepository

    internal val allWords: LiveData<List<Word>>?
    private val job = Job()

    init {
        mRepository = WordRepository(application)
        allWords = mRepository.allWords
    }



    fun insert(word: Word) {
        mRepository.insert(word)
    }

    fun deleteAll() {
        mRepository.deleteAllWords()
    }
}
