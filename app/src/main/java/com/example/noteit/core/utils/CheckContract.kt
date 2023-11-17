package com.example.noteit.core.utils

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContract
import com.example.noteit.ui.notes.CheckFragment
import com.example.noteit.ui.notes.NotesActivity


class CheckContract : ActivityResultContract<String,String>() {
    override fun createIntent(context: Context, input: String): Intent {
        val intent = Intent(context,NotesActivity::class.java)
        intent.putExtra("FEATURE",input)
        return intent
    }

    override fun parseResult(resultCode: Int, intent: Intent?): String {
        return NotesActivity.getInstance().noteTitle
    }
}