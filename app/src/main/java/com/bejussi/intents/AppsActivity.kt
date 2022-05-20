package com.bejussi.intents

import android.app.*
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_apps.*


class AppsActivity : AppCompatActivity(), View.OnClickListener {

    private val NOTIFY_ID = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apps)

        btnSetAlarm.setOnClickListener(this)
        btnShowMapLoc.setOnClickListener(this)
        btnSendAnEmail.setOnClickListener(this)
        btnStartPhoneCall.setOnClickListener(this)
        btnWebSearch.setOnClickListener(this)
        btnPendIntent.setOnClickListener(this)

        // Set up the notification channel that will be used for
        // the pending intent example
        // Set up the notification channel that will be used for
        // the pending intent example
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("my_channel", "Android Intents", importance)
            channel.description = "Test Notification Channel"
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(channel)
        }

    }

    override fun onClick(p0: View?) {
        when(p0) {
            btnSetAlarm -> setAlarm()
            btnShowMapLoc -> showMapLoc()
            btnSendAnEmail -> sendAnEmail()
            btnStartPhoneCall -> startPhoneCall()
            btnWebSearch -> webSearch()
            btnPendIntent -> pendIntent()
        }
    }

    private fun setAlarm() {
        val message = "Time to wake up!";
        val hour = 6;
        val minutes = 30;

        // TODO: Create an intent to tell the system to set an alarm
        // NOTE: your app needs to have the Set Alarm permission
        val intent = Intent(AlarmClock.ACTION_SET_ALARM).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE,message)
            putExtra(AlarmClock.EXTRA_HOUR,hour)
            putExtra(AlarmClock.EXTRA_MINUTES,minutes)
            putExtra(AlarmClock.EXTRA_VIBRATE,true)
        }

        startActivity(intent)
    }

    private fun showMapLoc() {
        // Locations can be specified using latlongs, queries, addresses, etc.
//      val location = "geo:37.4220,-122.0841"
//      val location = "geo:0,0?q=37.4220,-122.0841(GooglePlex)";
//      val location = "geo:0,0?q=20+W+34th+St+10001";
        val location = "geo:47.6205,-122.3493?q=restaurants";

        // TODO: Parse the location using the Uri class
        val geoLocUri = Uri.parse(location)

        // TODO: Pass the Uri directly to the Intent constructor
        val intent = Intent(Intent.ACTION_VIEW, geoLocUri)

        startActivity(intent)
    }

    private fun sendAnEmail() {
        val addresses = arrayOf("test@example.com")
        val ccs = arrayOf("someone@example.com")
        val subject = "This is a test"
        val message = "This is a test email message!"

        // TODO: Use setData to ensure that only email apps respond
        // TODO: add the email data to the intent
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailTo:")
            putExtra(Intent.EXTRA_EMAIL,addresses)
            putExtra(Intent.EXTRA_SUBJECT,subject)
            putExtra(Intent.EXTRA_CC,ccs)
            putExtra(Intent.EXTRA_TEXT,message)
        }

        startActivity(intent)
    }

    private fun startPhoneCall() {
        val phoneNumber = "1-800-555-1212"

        // TODO: Build the Uri for the phone number
        val numUri = Uri.parse("tel:" + phoneNumber)

        // TODO: Your application needs the CALL_PHONE permission for this intent
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = numUri
        }

        // TODO: Set the Uri as the intent data
        startActivity(intent)
    }

    private fun webSearch() {
        val queryStr = "Eiffel Tower"

        // TODO: Create an intent to fire off a web search
        // TODO: pass the query string to the Intent
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY,queryStr)
        }
        startActivity(intent)
    }

    private fun pendIntent() {
        val builder = NotificationCompat.Builder(this, "my_channel")

        // TODO: Create the intent that will be fired when the user taps the notification
        val intent = Intent(this,DestinationActivity::class.java).apply {
            putExtra("StringValue","Hello from notification!")
            putExtra("IntValue",12345)
        }
        // TODO: Wrap it up in a PendingIntent
        val pendingIntent = PendingIntent.getActivity(this,NOTIFY_ID, intent, PendingIntent.FLAG_CANCEL_CURRENT)

        // TODO: Wrap it up in a PendingIntent
        builder.setSmallIcon(R.drawable.ic_launcher_foreground)
        builder.setContentTitle("Sample Notification")
        builder.setContentText("This is a sample notification")
        builder.setAutoCancel(true)
        builder.setSubText("Tap to view")

        // TODO: add the pending intent to the builder
        builder.setContentIntent(pendingIntent)

        val notification: Notification = builder.build()
        val mgr = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mgr.notify(NOTIFY_ID, notification)
    }
}