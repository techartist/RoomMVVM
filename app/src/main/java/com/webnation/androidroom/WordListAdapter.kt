package com.webnation.androidroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.webnation.androidroom.model.Word


class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val mInflater: LayoutInflater
    private var mWords: List<Word>? = null // Cached copy of words

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
        if (mWords != null) {
            val current = let {mWords!![p1]}
            p0.wordItemView.setText(current.word)
        } else {
            // Covers the case of data not being ready yet.
            p0.wordItemView.text = "No Word"
        }
    }



    override fun getItemCount(): Int {
        var i = 0;
         val int = mWords.let { it?.size }
        if (int != null) return int
        else return 0
    }



    class WordViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wordItemView: TextView

        init {
            wordItemView = itemView.findViewById(R.id.textView)
        }
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }



    internal fun setWords(words: List<Word>) {
        mWords = words
        notifyDataSetChanged()
    }

    internal fun deleteAllWords() {
        mWords = emptyList()
        notifyDataSetChanged()
    }
}
