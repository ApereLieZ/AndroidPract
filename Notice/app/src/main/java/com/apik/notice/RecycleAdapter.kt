package com.apik.notice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MainAdapter(private val newList : ArrayList<Notice>): RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,
        parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = newList[position]

        holder.date.text = currentItem.date.toString()
        holder.title.text = currentItem.title
    }
    override fun getItemCount(): Int {
        return newList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        //val image :ShapeableImageView = itemView.findViewById(R.id.imageOfNotice)
        val title :TextView = itemView.findViewById(R.id.titleOfNotice)
        val date :TextView = itemView.findViewById(R.id.dateOfNotice)

    }

}

