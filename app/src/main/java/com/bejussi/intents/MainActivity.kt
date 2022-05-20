package com.bejussi.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        create_implicit.setOnClickListener(this)
        create_explicit.setOnClickListener(this)
        btnMediaIntents.setOnClickListener(this)
        btnAppIntents.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when(p0) {
            create_implicit -> createImplicitIntent()
            create_explicit -> createExplicitIntent()
            btnMediaIntents -> openMediaIntent()
            btnAppIntents -> openAppsIntent()
        }
    }

    private fun openAppsIntent() {
        val intent = Intent(this, AppsActivity::class.java)
        startActivity(intent)
    }

    private fun openMediaIntent() {
        val intent = Intent(this,MediaActivity::class.java)
        startActivity(intent)
    }

    private fun createExplicitIntent() {
        // TODO: Build an explicit Intent to launch our Activity
        val intent = Intent(this, DestinationActivity::class.java)

        // TODO: send data along with the Intent to the destination
        intent.putExtra("StringValue","Hello!")
        intent.putExtra("IntValue",12345)

        // TODO: Start the activity with our explicit intent
        startActivity(intent)

    }

    private fun createImplicitIntent() {
        // TODO: Build an implicit intent to handle a type of action
        val stringMessage = R.string.stringMessage
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,stringMessage)
        }
        // TODO: use an intent chooser to force a choose dialog


        // TODO: Verify that the intent will resolve to an activity
        startActivity(intent)
        // Typically you would handle the null case here by informing the user
        // that there is no installed app to handle this intent or
        // by taking some other action
    }
}