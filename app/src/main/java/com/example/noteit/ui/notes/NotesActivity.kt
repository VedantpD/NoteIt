package com.example.noteit.ui.notes


import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.noteit.R
import com.example.noteit.core.base.BaseActivity
import com.example.noteit.core.utils.SessionManager
import com.example.noteit.core.utils.repository.NotesRepository
import com.example.noteit.core.utils.roomDb.NotesDatabase
import com.example.noteit.databinding.ActivityNotesBinding
import com.example.noteit.model.Note
import kotlinx.coroutines.launch

class NotesActivity : BaseActivity() {

    private lateinit var binding : ActivityNotesBinding
    private lateinit var repository: NotesRepository

    val checkFragment = CheckFragment()
    val textFragment = TextFragment()
    private lateinit var sessionManager : SessionManager



    var noteTitle = ""
    var noteTextInfo = ""

    companion object {
        private var instance: NotesActivity? = null

        fun getInstance(): NotesActivity {
            return instance ?: throw IllegalStateException("NotesActivity not initialized")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        instance = this

        repository = NotesRepository(NotesDatabase.getDatabase(this).noteDao())
        sessionManager = SessionManager(this)
        setClicks()
        var fragName = intent.getStringExtra("FEATURE")
        if(fragName=="Check"){
            setFragment(checkFragment)
        }else if(fragName=="Text"){
            setFragment(textFragment)
        }else{

        }
    }

    private fun setClicks() {
        binding.btnSaveNote.setOnClickListener {
            var fragName = intent.getStringExtra("FEATURE")
            if(fragName=="Check") {
                noteTitle = checkFragment.binding.etxtTitle.text.toString()
                if (noteTitle.isEmpty()) {
                    showToast("Enter a title for your note")
                    return@setOnClickListener
                }
                finish()
            }else if(fragName=="Text"){
                noteTitle = textFragment.binding.etxtTitle.text.toString()
                noteTextInfo = textFragment.binding.etxtTextInfo.text.toString()
                Log.d("main",noteTextInfo)
                if (noteTitle.isEmpty()) {
                    showToast("Enter a title for your note")
                    return@setOnClickListener
                }
                saveTextData(noteTextInfo)
                finish()
            }
        }
    }

    private fun setFragment(frag : Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.frameLayout1,frag)
            commit()
        }
    }
    private fun saveTextData(value: String? = null) {
        lifecycleScope.launch {
            try {
                if (value != null) {
                    val temp = mutableListOf(value)
                    val note = Note(null,temp)
                    repository.insertData(note)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        sessionManager.setCount(sessionManager.getCount() + 1)
    }
}