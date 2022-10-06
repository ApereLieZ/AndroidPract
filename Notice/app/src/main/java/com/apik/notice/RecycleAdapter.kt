package com.apik.notice

import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URI

class MainAdapter(private val newList : ArrayList<Notice>): RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.notification_item,
        parent, false)
        return CustomViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.date.text = currentItem.date
        holder.title.text = currentItem.title
        holder.setImage(currentItem.image)

    }
    override fun getItemCount(): Int {
        return newList.size
    }

    class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image :ImageView = itemView.findViewById(R.id.imageOfNotice)
        val title :TextView = itemView.findViewById(R.id.titleOfNotice)
        val date :TextView = itemView.findViewById(R.id.dateOfNotice)

        fun setImage(imageUri: Uri?){
            if(imageUri == null)
                image.setImageResource(R.drawable.ic_launcher_foreground)
            else{
                image.setImageURI(imageUri)
            }
        }
    }

}

