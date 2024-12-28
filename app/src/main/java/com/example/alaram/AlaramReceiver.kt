package com.example.alaram


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.media.Ringtone
import android.os.Vibrator
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Play alarm sound
        val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        val ringtone: Ringtone = RingtoneManager.getRingtone(context, ringtoneUri)
        RingtoneManagerSingleton.ringtone = ringtone  // Store in the singleton
        ringtone.play()

        // Vibrate the phone for 1 second
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        vibrator.vibrate(1000)  // Vibrate for 1 second

        Log.d("AlarmReceiver", "Alarm triggered!")
    }
}
object RingtoneManagerSingleton {
    var ringtone: Ringtone? = null
}


    fun stopRingtone() {
        if (RingtoneManagerSingleton.ringtone != null) {
            Log.d("AlarmApp", "Stopping ringtone...")
            RingtoneManagerSingleton.ringtone?.stop()
            RingtoneManagerSingleton.ringtone = null  // Clear the reference
        } else {
            Log.d("AlarmApp", "No ringtone to stop!")
        }
    }


