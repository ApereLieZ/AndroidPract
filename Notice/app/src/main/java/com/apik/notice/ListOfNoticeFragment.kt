package com.apik.notice

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment

import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

interface OnItemClickListener {
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener) {
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {
        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }
        }
    })
}

class ListOfNotice : Fragment() {

    private lateinit var adapter: MainAdapter
    private lateinit var recyclerView: RecyclerView


    lateinit var imageId: ArrayList<Int>
    lateinit var title: ArrayList<String>
    lateinit var date: ArrayList<Int>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val rootView = inflater.inflate(R.layout.fragment_list_of_notice, container, false)

        val btn = rootView.findViewById<FloatingActionButton>(R.id.noticeCreatorBtn)

        btn.setOnClickListener {
            val intent = Intent(activity, CreateNoticeActivity::class.java)
            startActivity(intent)
        }

        return rootView
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(false)

        adapter = MainAdapter(NoticeData.noticeArray)
        recyclerView.adapter = adapter

        recyclerView.addOnItemClickListener(object: OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                val itemDetails = NoticeDetailsActivity(NoticeData.noticeArray[position])
                activity?.supportFragmentManager?.beginTransaction()
                    ?.hide(this@ListOfNotice)
                    ?.add(R.id.fragmentContainer, itemDetails)
                    ?.addToBackStack(null)
                    ?.commit()
            }
        })
    }


}