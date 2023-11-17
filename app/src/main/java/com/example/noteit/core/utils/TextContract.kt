package com.example.noteit.core.utils

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.example.noteit.ui.notes.NotesActivity


class TextContract : ActivityResultContract<String,String>() {
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context, NotesActivity::class.java)
        intent.putExtra("FEATURE",input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return NotesActivity.getInstance().noteTitle
    }
}