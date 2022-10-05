package com.apik.notice

import android.text.Editable
import android.net.Uri
object NoticeData {
    var noticeArray: ArrayList<Notice> = ArrayList()
}

data class Notice(var title: Editable?,
                  var description: Editable?,
                  var date: String,
                var imageURI: Uri? )