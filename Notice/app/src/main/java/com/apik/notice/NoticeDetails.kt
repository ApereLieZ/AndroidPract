package com.apik.notice

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat


class NoticeDetails(var noticeDetails: Notice): Fragment() {

    var title: TextView? = null
    var description: TextView? = null
    var image: ImageView? = null
    var date: TextView? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_notice_details, container, false)
        title = rootView.findViewById(R.id.detaileTitle)
        description = rootView.findViewById(R.id.detaileDescription)
        image = rootView.findViewById(R.id.detaileImage)
        date = rootView.findViewById(R.id.detaileDate)
        return  rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title?.text = noticeDetails.title
        description?.text = noticeDetails.description

        if(noticeDetails.imageURI != null){
            image?.setImageURI(noticeDetails.imageURI)
        }else{
            image?.setImageResource(R.drawable.ic_launcher_foreground)
        }
        date?.text = noticeDetails.date
    }




}