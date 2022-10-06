package com.apik.notice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


class NoticeDetailsActivity(var noticeDetails: Notice): Fragment() {

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
    fun getGaleryPermission(){
        requestPermissions(arrayOf("android.permission.WRITE_EXTERNAL_STORAGE"), 80)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGaleryPermission()
        title?.text = noticeDetails.title
        description?.text = noticeDetails.description

        val contentResolver = context?.contentResolver





        if(noticeDetails.image != null){
            image?.setImageURI(noticeDetails.image)
        }else{
            image?.setImageResource(R.drawable.ic_launcher_foreground)
        }
        date?.text = noticeDetails.date
    }




}