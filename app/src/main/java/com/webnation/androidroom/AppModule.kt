package com.webnation.androidroom

import android.app.Application
import com.webnation.androidroom.model.WordRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of WordRepository
    single<WordRepository> { WordRepository(application = Application()) }

    // scope for MainActivity
    scope<MainActivity> {
    }

    // MyViewModel ViewModel
    viewModel { WordViewModel(get()) }
}