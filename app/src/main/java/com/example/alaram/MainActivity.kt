package com.example.alaram

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.activity.ComponentActivity
import androidx.annotation.RequiresApi
import java.util.*

class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Get references to your TimePicker and Button
        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val setAlarmButton: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.textView)

        setAlarmButton.setOnClickListener {
            // Get the hour and minute from the TimePicker
            val hour = timePicker.hour
            val minute = timePicker.minute

            // Display the selected time in the TextView
            val formattedTime = String.format("%02d:%02d", hour, minute)
            textView.text = "Alarm set for: $formattedTime"

            // Set the alarm using the selected time
            setAlarm(hour, minute)
        }
    }

    // Function to set the alarm
    private fun setAlarm(hour: Int, minute: Int) {
        // Get the current time
        val calendar = Calendar.getInstance()

        // Set the time for the alarm
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        // Create an Intent to trigger the AlarmReceiver
        val intent = Intent(this@MainActivity, AlarmReceiver::class.java)

        // Create a PendingIntent to send with the AlarmManager
        val pendingIntent = PendingIntent.getBroadcast(this@MainActivity, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        // Create an AlarmManager instance
        val alarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Schedule the alarm using setExact to fire at the exact time
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
        val stopButton: Button = findViewById(R.id.stop_button)
        stopButton.setOnClickListener {
            stopRingtone()  // Stop the ringtone when the button is clicked
        }

    }
}

