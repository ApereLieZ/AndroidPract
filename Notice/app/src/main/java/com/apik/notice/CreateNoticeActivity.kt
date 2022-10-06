package com.apik.notice


import android.content.ContentValues
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



class CreateNoticeActivity : AppCompatActivity() {


    private lateinit var titleField: EditText
    private lateinit var descriptionField: EditText
    private lateinit var imageSelectBtn: Button
    private lateinit var pushNoticeBtn: Button
    private lateinit var takePhotoBtn: Button
    private lateinit var imageField: ImageView

    private var imageURI: Uri? = null


    private val pickImage = 100
    private val IMAGE_CAPTURE_CODE = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notice)
        titleField = findViewById(R.id.editTextNoticeTitle)
        descriptionField =findViewById(R.id.editTextNoticeDescription)
        imageSelectBtn = findViewById(R.id.buttonSelectImageNotice)
        pushNoticeBtn = findViewById(R.id.noticeButton)
        takePhotoBtn = findViewById(R.id.buttonTakePhotoNotice)

        imageField = findViewById(R.id.imageNotice)

        pushNoticeBtn.setOnClickListener {
            createNotice()
            finish()
        }

        takePhotoBtn.setOnClickListener{
            takeAPhoto()
        }

        imageSelectBtn.setOnClickListener{
            pickImageFromGallery()
        }

    }

    fun pickImageFromGallery(){
        val gallery = Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, pickImage)
    }

    fun takeAPhoto(){
        requestPermissions(arrayOf("android.permission.CAMERA"), 80)
        openCameraInterface()
    }

    private fun openCameraInterface() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "take pictures")
        values.put(MediaStore.Images.Media.DESCRIPTION, "nice desc")

        imageURI = applicationContext?.contentResolver?.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI)

        startActivityForResult(intent, IMAGE_CAPTURE_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK && requestCode == pickImage) {
            imageURI = data?.data
            imageField.setImageURI(imageURI)
        }
        if(resultCode == RESULT_OK && requestCode == IMAGE_CAPTURE_CODE){
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