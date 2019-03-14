package com.webnation.androidroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.webnation.androidroom.model.Word
import kotlinx.coroutines.runBlocking

import org.koin.androidx.viewmodel.ext.viewModel

class MainActivity : AppCompatActivity() {

    //private var mWordViewModel: WordViewModel? = null
    val NEW_WORD_ACTIVITY_REQUEST_CODE = 1
    lateinit var adapter : WordListAdapter

    // Lazy Inject ViewModel
    val mWordViewModel: WordViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val fab = findViewById(R.id.fab) as FloatingActionButton
        fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE)
        })
        val recyclerView = findViewById(R.id.recyclerview) as RecyclerView
        adapter = WordListAdapter(this)
        recyclerView.setAdapter(adapter)
        recyclerView.setLayoutManager(LinearLayoutManager(this))
        //mWordViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)

        mWordViewModel.allWords.observe(this, object : Observer<List<Word>> {
            override fun onChanged(words: List<Word>?) {
                // Update the cached copy of the words in the adapter.
                if (words != null) {
                    adapter.setWords(words)
                }
            }
        })

        runBlocking { if (mWordViewModel.countAllWords() == 0)  {
            mWordViewModel.populateDatabase()
        }}
    }



    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val word = Word(data.getStringExtra(NewWordActivity.EXTRA_REPLY))
                mWordViewModel.insert(word)
            }

        } else {
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            mWordViewModel.deleteAllWords()
            adapter.deleteAllWords()
            return true
        } else super.onOptionsItemSelected(item)

    }
}
