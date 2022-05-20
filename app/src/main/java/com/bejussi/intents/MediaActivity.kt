package com.bejussi.intents

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_media.*

class MediaActivity : AppCompatActivity(), View.OnClickListener {

    private val GET_IMAGE_CAPTURE = 1000


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_media)

        btnStartCamera.setOnClickListener(this)
        btnCapturePic.setOnClickListener(this)
        btnSendText.setOnClickListener(this)
        btnOpenURL.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            btnStartCamera -> startCamera()
            btnCapturePic -> capturePic()
            btnSendText -> sendText()
            btnOpenURL -> openURL()
        }
    }

    private fun openURL() {
        val url = "http://www.google.com"
        // TODO: Parse the URL string using the Uri class
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW,webpage)
        startActivity(intent)
    }

    private fun sendText() {
        val message = getString(R.string.stringMessage)

        // TODO: Use the setData function to indicate the type of data that will be sent
        // this will help the system figure out what apps to include in the chooser
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("sms:18885551212")
            putExtra("sms_body","$message")
        }

        startActivity(intent)
    }

    private fun capturePic() {
        // Take a picture and consume the returned result bitmap
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,GET_IMAGE_CAPTURE)
    }

    private fun startCamera() {
        // TODO: Start the camera in photo mode
        val intent = Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA)
        startActivity(intent)
    }

    // This function will be called when an activity that was started for the purpose
    // of returning a result has some data for our app to process
    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GET_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // TODO: Retrieve the data from the result intent and look for the bitmap
            if (resultCode == Activity.RESULT_OK && requestCode == GET_IMAGE_CAPTURE && data != null){
                imgCapturePic.setImageBitmap(data.extras?.get("data") as Bitmap)
            }
        }
    }
}