package com.apik.notice


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter



class CreateNotice : AppCompatActivity() {


    private lateinit var titleField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var imageSelectBtn: Button
    private lateinit var pushNoticeBtn: Button
    private lateinit var imageField: ImageView

    private var imageURI: Uri? = null


    private val pickImage = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notice)
        titleField = findViewById(R.id.editTextNoticeTitle)
        descriptionField =findViewById(R.id.editTextNoticeDescription)
        imageSelectBtn = findViewById(R.id.buttonSelectImageNotice)
        pushNoticeBtn = findViewById(R.id.noticeButton)

        imageField = findViewById(R.id.imageNotice)

        pushNoticeBtn.setOnClickListener {
            createNotice()
            finish()
        }

        imageSelectBtn.setOnClickListener{
            pickImageFromGallery()
        }

    }

    fun pickImageFromGallery(){
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == pickImage) {
            imageURI = data?.data
            imageField.setImageURI(imageURI)
        }
    }


    private fun createNotice(){
        val title = titleField.text
        val data = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
        val formatted = data.format(formatter)
        val description = descriptionField.text
        NoticeData.noticeArray.add(Notice(title, description, formatted, imageURI))
    }



}