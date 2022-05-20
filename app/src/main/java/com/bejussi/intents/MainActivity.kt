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
    }

    override fun onClick(p0: View?) {
        when(p0) {
            create_implicit -> createImplicitIntent()
            create_explicit -> createExplicitIntent()
        }
    }

    private fun createExplicitIntent() {
        // TODO: Build an explicit Intent to launch our Activity
        val intent = Intent(this, DestinationActivity::class.java)

        // TODO: send data along with the Intent to the destination


        // TODO: Start the activity with our explicit intent
        startActivity(intent)

    }

    private fun createImplicitIntent() {
        // TODO: Build an implicit intent to handle a type of action
        val stringMessage = R.string.stringMessage
        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT,stringMessage)
        // TODO: use an intent chooser to force a choose dialog


        // TODO: Verify that the intent will resolve to an activity
        startActivity(intent)
        // Typically you would handle the null case here by informing the user
        // that there is no installed app to handle this intent or
        // by taking some other action
    }
}