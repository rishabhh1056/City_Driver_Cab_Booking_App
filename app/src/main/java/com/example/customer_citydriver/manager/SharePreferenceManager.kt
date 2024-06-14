package com.example.customer_citydriver.manager

import android.content.Context

class SharePreferenceManager(context: Context) {

    val PREFS_NAME = "MyPrefsFile"
    val PRIVATE_MODE = 0
    val KEY_NAME = "name"
    val sharedPref = context.getSharedPreferences(PREFS_NAME, PRIVATE_MODE)

    fun saveData(name: String) {
        val editor = sharedPref.edit()
        editor.putString(KEY_NAME, name)
        editor.apply()
    }

    fun getData(): String? {
        return sharedPref.getString(KEY_NAME, null)
    }

    fun clearData() {
        val editor = sharedPref.edit()
        editor.clear()
        editor.apply()
    }

}