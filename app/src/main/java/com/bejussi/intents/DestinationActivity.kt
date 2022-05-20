package com.bejussi.intents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_destination.*

class DestinationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destination)

        // TODO: extract any data passed by the caller
        val callingIntentString = intent.getStringExtra("StringValue")
        val callingIntentInt = intent.getIntExtra("IntValue", -1)

        txtValues.text = getString(R.string.welcome_messages,callingIntentString,callingIntentInt)
    }
}