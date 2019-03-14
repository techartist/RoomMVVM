package com.webnation.androidroom.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "word_table")
class Word(@field:PrimaryKey
           @field:ColumnInfo(name = "word")
           val word: String)