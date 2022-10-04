package com.apik.notice

import android.os.Bundle
import androidx.fragment.app.Fragment

import android.view.View

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListOfNotice : Fragment() {

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var NoticeArray: ArrayList<Notice>


    lateinit var imageId: ArrayList<Int>
    lateinit var title: ArrayList<String>
    lateinit var date: ArrayList<Int>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInit()

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MainAdapter(NoticeArray)
        recyclerView.adapter = adapter
    }

    private fun dataInit() {
        NoticeArray = arrayListOf()

        imageId = arrayListOf(
            0, 1, 2, 3, 4, 5
        )

        title = arrayListOf(
            "get it",
            "get it",
            "not this",
            "buy milk",
            "kill me",
            "hello world"
        )

        date = arrayListOf(
            1, 2, 3, 4, 5, 6
        )

        for (i in 0..imageId.size) {
            val notice = Notice(title[i], imageId[i], date[i])
            NoticeArray.add(notice)
        }
    }
}