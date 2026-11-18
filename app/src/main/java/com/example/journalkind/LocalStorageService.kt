package com.example.journalkind

import android.content.Context

class LocalStorageService(context: Context) {
    private val prefs = context.getSharedPreferences("journalKind_prefs", Context.MODE_PRIVATE)

    // Sesión
    fun saveSession(token: String, name: String) {
        prefs.edit().putString(KEY_TOKEN, token).putString(KEY_NAME, name).apply()
    }
    fun getToken(): String? = prefs.getString(KEY_TOKEN, null)
    fun getUserName(): String? = prefs.getString(KEY_NAME, null)
    fun isLoggedIn(): Boolean = !getToken().isNullOrEmpty()
    fun clearSession() {
        prefs.edit().remove(KEY_TOKEN).remove(KEY_NAME).remove(KEY_EMAIL).apply()
    }

    fun saveEmail(email: String) {
        prefs.edit().putString(KEY_EMAIL, email).apply()
    }
    fun getEmail(): String? = prefs.getString(KEY_EMAIL, null)

    // Usuarios registrados
    fun saveUser(email: String, record: String) {
        prefs.edit().putString(USER_PREFIX + email, record).apply()
    }
    fun getUser(email: String): String? = prefs.getString(USER_PREFIX + email, null)

    companion object {
        private const val KEY_TOKEN = "auth_token"
        private const val KEY_NAME = "user_name"
        private const val KEY_EMAIL = "user_email"
        private const val USER_PREFIX = "user_"
    }
}