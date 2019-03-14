package com.webnation.androidroom

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.webnation.androidroom.model.Word


class WordListAdapter internal constructor(context: Context) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val mInflater: LayoutInflater = LayoutInflater.from(context)
    private var mWords = emptyList<Word>()


    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = mWords[position]
        holder.wordItemView.text = current.word
    }



    override fun getItemCount(): Int {
         return mWords.size
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
