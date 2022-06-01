package com.example.dermanalyze_bangkit_capstone

import android.content.Context

internal class LoginPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val TOKEN = "token"
        private const val ISLOGIN = "islogin"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun setToken(token: String) {
        editor.putString(TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return preferences.getString(TOKEN, null)
    }

    fun setIsLogin(isLogin: Boolean) {
        editor.putBoolean(ISLOGIN, isLogin)
        editor.apply()
    }

    fun getIsLogin(): Boolean {
        return preferences.getBoolean(ISLOGIN, false)
    }

    fun clearUser() {
        editor.clear()
        editor.apply()
    }

}