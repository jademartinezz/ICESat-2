package gov.nasa.gsfc.icesat2.icesat_2

import android.content.Context
import android.util.Log

private const val NOTIFICATION_SHARED_PREF = "gov.nasa.gsfc.icesat2.icesat_2.NotificationSharedPref"
private const val TAG = "NotificationsSharedPref"


class NotificationsSharedPref(context: Context) {
    private val sharedPreferences = context.getSharedPreferences(NOTIFICATION_SHARED_PREF, Context.MODE_PRIVATE)
    /**
     * 2nd param is string of
     * timeStampOfAlarm, lat, long, timeString
     */
    fun addToNotificationSharedPref(timestampOfFlyover: String, notificationInfoString: String) {
        Log.d(TAG, "addToNotificationSharedPref - NotificationsSharedPref")
        //Log.d(TAG, "Adding $timestampOfFlyover to sharedPref")
        with(sharedPreferences.edit()) {
            putString(timestampOfFlyover, notificationInfoString)
            apply()
        }
    }

    fun delete(timeKey: String) {
        Log.d(TAG, "delete - NotificationsSharedPref")
        with(sharedPreferences.edit()) {
            val any = try {
                remove(timeKey)
                apply()
            } catch (e: Exception) {
                Log.d(TAG, "element not in sharedPreferences")
            }
            any
        }
    }

    fun contains(timestamp: String): Boolean {
        Log.d(TAG, "contains - NotificationsSharedPref")
        return sharedPreferences.contains(timestamp)
    }

    fun printAll() {
        Log.d(TAG, "printAll - NotificationsSharedPref")
        Log.d(TAG, "print all keys called")
        val allEntries = sharedPreferences.all
        val keys = allEntries.keys
        Log.d(TAG, "allEntriesSize is ${allEntries.size}")
        for (element in keys) {
            Log.d(TAG, "key is: $element value is: ${sharedPreferences.getString(element, "Unknown??")}")
        }
    }

    fun getSharedPrefValues() = sharedPreferences.all.values

    fun getSharedPrefKeys() = sharedPreferences.all.keys

    fun get(key: String) = sharedPreferences.getString(key, "")

}