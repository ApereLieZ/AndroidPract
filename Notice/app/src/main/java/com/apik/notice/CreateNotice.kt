package com.apik.notice


import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager

import android.media.Image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast


class CreateNotice : AppCompatActivity() {


    lateinit var titleField: EditText
    lateinit var descriptionField: EditText
    lateinit var imageSelectBtn: Button
    lateinit var imageField: ImageView

    lateinit var title: String
    lateinit var description: String
    lateinit var image: Image



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_notice)
        titleField = findViewById(R.id.editTextNoticeTitle)
        descriptionField =findViewById(R.id.editTextNoticeDescription)

        imageSelectBtn = findViewById<Button>(R.id.buttonSelectImageNotice)

    }


    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_view.setImageURI(data?.data)
        }
    }


}