package com.example.kotlinproject

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShowVH(itemview: View, _context : Context): RecyclerView.ViewHolder(itemview), IViewHolderAdapter<String> {

    var text : TextView = itemView.findViewById<TextView>(R.id.item_list);

    override fun LoadView(item: String) {
        text.text = item;
    }
}