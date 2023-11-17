package com.example.noteit.ui


import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.noteit.core.base.BaseActivity
import com.example.noteit.core.utils.repository.NotesRepository
import com.example.noteit.core.utils.repository.YourRepository
import com.example.noteit.core.utils.roomDb.NotesDatabase
import com.example.noteit.core.utils.roomDb.YourDatabase
import com.example.noteit.databinding.ActivityNotesDetailBinding
import com.example.noteit.model.YourEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotesDetailActivity : BaseActivity() {
    private lateinit var binding : ActivityNotesDetailBinding
    private lateinit var repository: NotesRepository
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value = intent.getStringExtra("itemId")
        repository = NotesRepository(NotesDatabase.getDatabase(this).noteDao())
        textView = TextView(this)

        if (value != null) {
            getTextUI(value.toLong())
        }
    }
    private fun getTextUI(value: Long) {
        lifecycleScope.launch {
            try {
                val data = repository.getIdData(value)
                withContext(Dispatchers.Main) {
                    textView.text = data.info?.get(0) ?: "Not found"
                    textView.setTextSize(5, 7F)
                    binding.notesLayout.addView(textView)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}