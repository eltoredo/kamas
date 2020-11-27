package com.example.kotlinproject

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.core.content.contentValuesOf
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView.*

class ShowAdapter(private  var fragmentActivityCurrent : FragmentActivity?,
                  private var context:Context?) : Adapter<ShowVH>()
{

    private  var _shows: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowVH {
        var inflater :  LayoutInflater =  LayoutInflater.from(parent.context)
        var view: View =  inflater.inflate(R.layout.list_item_view,parent,false)

        return  ShowVH(view,context!! )
    }

    override fun onBindViewHolder(holder: ShowVH, position: Int) {
        var showCurrent: String =  _shows[position]
        holder.LoadView(showCurrent)
    }

    override fun getItemCount(): Int {
        return _shows.size
    }

    fun setData(list: ArrayList<String>) {
        this._shows.addAll(list)
        notifyDataSetChanged()
    }
}