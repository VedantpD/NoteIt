package com.example.noteit.adapter

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.noteit.R
import com.example.noteit.core.utils.SessionManager
import com.example.noteit.model.YourEntity
import com.example.noteit.ui.NotesDetailActivity


// TO DO : private lateinit var  binding : ItemLayoutBinding

class MyAdapter(val context: Context,val list: LinkedHashMap<String, String>):RecyclerView.Adapter<MyAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle : TextView = itemView.findViewById(R.id.txtItemTitle)
        val txtType : TextView = itemView.findViewById(R.id.txtType)
        val txtId : TextView = itemView.findViewById(R.id.txtId)
        val layoutItem : LinearLayout = itemView.findViewById(R.id.lLayoutItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val keysList = list.keys.toList()
        val sessionManager = SessionManager(context)

        holder.txtTitle.text = keysList[position]
        //holder.txtId.text = sessionManager.getCount().toString()
        holder.txtId.text = (position+1).toString()


        val type =list[keysList[position]]
        holder.txtType.text = type

        if(type=="Text"){
            holder.layoutItem.setBackgroundResource(R.drawable.text_cardview_background)
        }else{
            holder.layoutItem.setBackgroundResource(R.drawable.text_cardview_background)
        }

        holder.itemView.setOnClickListener {
            val text = holder.txtId.text.toString()
            Toast.makeText(context, "Clicked: $text", Toast.LENGTH_SHORT).show()

            val intent = Intent(context, NotesDetailActivity::class.java)
            intent.putExtra("itemId", holder.txtId.text.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}